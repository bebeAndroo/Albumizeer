package com.yenimobile.albumizeer.ui

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.yenimobile.albumizeer.EXTRA_ALBUM_KEY
import com.yenimobile.albumizeer.R
import com.yenimobile.albumizeer.database.DeezAlbumDAO
import com.yenimobile.albumizeer.models.DeezAlbum
import com.yenimobile.albumizeer.network.DeezApi
import com.yenimobile.albumizeer.ui.adapters.AlbumsAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import javax.inject.Inject

class ListingalbumsViewModel(private val deezAlbumDAO: DeezAlbumDAO, private val activity: AppCompatActivity)
    : BaseViewModel(), AlbumsAdapter.OnAlbumClickListener{


    override fun onAlbumClick(deezAlbum: DeezAlbum) {
        Log.e("albumitem", "click listener is triggered susscessfully")
        val intent = Intent(activity, AlbumdetailsActivity::class.java)
        intent.putExtra(EXTRA_ALBUM_KEY, deezAlbum as Serializable)
        activity.startActivity(intent)
    }


    @Inject
    lateinit var deezApi: DeezApi

    val albumAdapter: AlbumsAdapter = AlbumsAdapter(this)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadAlbums()
    }



    private lateinit var disposable: Disposable

    init {
        loadAlbums()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun loadAlbums(){

        Log.e("loadalbums", "load albums is triggered ")

        disposable = Observable.fromCallable {
            deezAlbumDAO.all }
            .concatMap { dbDeezList ->
                if(dbDeezList.isEmpty())
                    deezApi.getDeezData().concatMap { apiData ->
                        deezAlbumDAO.insertAll(*apiData.data.toTypedArray())
                        Observable.just(apiData.data)
                    }
                else
                    Observable.just(dbDeezList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onRetrieveAlbumsListStart()
            }
            .doOnTerminate {
                onRetrieveAlbumsListFinish()
            }
            .subscribe(
                { result ->
                    onRetrieveAlbumsListSuccess(result)
                },
                {
                    onRetrieveAlbumsListError()
                    Log.e("error", "error ${it.message}")
                }
            )
    }


    private fun onRetrieveAlbumsListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveAlbumsListFinish(){
        Log.e("loadalbums", "on tretrieve post list finished")
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveAlbumsListSuccess(albumList: List<DeezAlbum>){
        albumAdapter.updateAlbumList(albumList)

    }

    private fun onRetrieveAlbumsListError(){
        Log.e("loadalbums", "post list error triggered")
        errorMessage.value = R.string.album_error
    }









}//class
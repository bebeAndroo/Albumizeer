package com.yenimobile.albumizeer.ui

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import com.yenimobile.albumizeer.R
import com.yenimobile.albumizeer.models.DeezAlbum
import com.yenimobile.albumizeer.models.DeezArtist

class DeezAlbumViewModel: BaseViewModel(){


    private val deezAlbumTitle = MutableLiveData<String>()
    private val deezAlbumUrl = MutableLiveData<String>()
    private val deezAlbumNbTrack = MutableLiveData<Int>()
    private val deezAlbumReleaseDate = MutableLiveData<String>()
    private val deezAlbumArtist = MutableLiveData<DeezArtist>()

    private val deezArtistName = MutableLiveData<String>()
    private val deezArtistPicture = MutableLiveData<String>()

    fun bind(deezAlbum: DeezAlbum){
        deezAlbumTitle.value = deezAlbum.title
        deezAlbumUrl.value = deezAlbum.cover_xl
        deezAlbumNbTrack.value = deezAlbum.nb_tracks
        deezAlbumReleaseDate.value = deezAlbum.release_date
        deezAlbumArtist.value = deezAlbum.artist

        deezArtistName.value = deezAlbum.artist.name
        deezArtistPicture.value = deezAlbum.artist.picture_medium
    }


    fun getDeezAlbumTitle(): MutableLiveData<String>{
        return deezAlbumTitle
    }

    fun getDeezArtisteName(): MutableLiveData<String> {
        return deezArtistName
    }

    fun loadAlbumImage(imageView: ImageView){
        Picasso.get()
            .load(deezAlbumUrl.value)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    fun loadArtistImage(imageView: ImageView){
        Picasso.get()
            .load(deezArtistPicture.value)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

}




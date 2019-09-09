package com.yenimobile.albumizeer.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.yenimobile.albumizeer.EXTRA_ALBUM_KEY
import com.yenimobile.albumizeer.R
import com.yenimobile.albumizeer.databinding.ActivityAlbumdetailsBinding
import com.yenimobile.albumizeer.models.DeezAlbum

class AlbumdetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAlbumdetailsBinding
    private lateinit var viewModel: DeezAlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_albumdetails)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_albumdetails)

        if (intent.hasExtra(EXTRA_ALBUM_KEY)){
            val deezAlbum: DeezAlbum =
                intent.getSerializableExtra(EXTRA_ALBUM_KEY) as DeezAlbum
            Log.e("album details", "intent has extra named : ${deezAlbum.title}")

            viewModel = ViewModelProviders.of(this).get(DeezAlbumViewModel::class.java)
            viewModel.bind(deezAlbum)
            viewModel.loadAlbumImage(binding.detailsAlbumIV)
            viewModel.loadArtistImage(binding.detailsArtisteIV)

            binding.albumviewmodel = viewModel


            var actionBar = supportActionBar
            actionBar!!.title = deezAlbum.title
            actionBar.setDisplayHomeAsUpEnabled(true)

        }
    }//oncreate

    override fun onSupportNavigateUp(): Boolean {
        //return super.onSupportNavigateUp()
        onBackPressed()
        return true
    }


}//activity

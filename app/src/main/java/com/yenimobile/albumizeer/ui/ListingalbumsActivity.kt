package com.yenimobile.albumizeer.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yenimobile.albumizeer.R
import com.yenimobile.albumizeer.databinding.ActivityListingalbumsBinding

class ListingalbumsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListingalbumsBinding
    private lateinit var viewModel: ListingalbumsViewModel

    private var errorSnackbar: Snackbar? = null

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_listingalbums)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_listingalbums)

        gridLayoutManager = GridLayoutManager(this, 2)
        binding.albumRV.layoutManager = gridLayoutManager


        viewModel =
            ViewModelProviders.of(this, ListingAlbumsViewModelFactory(this))
                .get(ListingalbumsViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer {errorMessage ->
            if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel
        //binding.setLifecycleOwner(this)




    }//onCreate


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }






}//activity

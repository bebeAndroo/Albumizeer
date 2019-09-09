package com.yenimobile.albumizeer.ui

import androidx.lifecycle.ViewModel
import com.yenimobile.albumizeer.dagger.DaggerViewModelInjector
import com.yenimobile.albumizeer.dagger.NetworkModule
import com.yenimobile.albumizeer.dagger.ViewModelInjector

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector =
        DaggerViewModelInjector//DaggerViewModelInjector vailable to import after compile
            .builder()
            .networkModule(NetworkModule)
            .build()


    init {
        inject()
    }

    private fun inject(){
        when(this ){
            is DeezAlbumViewModel -> injector.inject(this)
            is ListingalbumsViewModel -> injector.inject(this)
        }
    }

}
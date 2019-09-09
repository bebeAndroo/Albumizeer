package com.yenimobile.albumizeer.dagger

import com.yenimobile.albumizeer.ui.DeezAlbumViewModel
import com.yenimobile.albumizeer.ui.ListingalbumsViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {


    fun inject(deezAlbumViewModel: DeezAlbumViewModel)
    fun inject(listingalbumsViewModel: ListingalbumsViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }

}
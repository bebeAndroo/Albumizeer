package com.yenimobile.albumizeer.dagger

import com.yenimobile.albumizeer.BASE_URL
import com.yenimobile.albumizeer.network.DeezApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module

@Suppress("unused")
object NetworkModule {

    /**
     * Provides the DeezAlbum service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the DeezAlbum service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providesDeezApi(retrofit: Retrofit): DeezApi {
        return retrofit.create(DeezApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providesRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            )
            .build()
    }
}
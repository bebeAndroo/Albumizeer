package com.yenimobile.albumizeer

import com.yenimobile.albumizeer.models.DeezData
import com.yenimobile.albumizeer.network.DeezApi
import com.yenimobile.albumizeer.utils.ALBUM_MOCK_PATH
import com.yenimobile.albumizeer.utils.ApiUtils
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    @Provides
    @Singleton
    internal fun provideDeezApi(retrofit: Retrofit): DeezApi {
        return object : DeezApi {
            override fun getDeezData(): Observable<DeezData> {
                return Observable.fromCallable { ApiUtils.getUrl<DeezData>(ALBUM_MOCK_PATH) }
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).build()
    }
}



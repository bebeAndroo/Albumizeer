package com.yenimobile.albumizeer.network

import com.yenimobile.albumizeer.models.DeezData
import io.reactivex.Observable
import retrofit2.http.GET

interface DeezApi {
    /**
     * Deezer api 2.0
     */

    @GET("/2.0/user/2529/albums")
    fun getDeezData(): Observable<DeezData>
}
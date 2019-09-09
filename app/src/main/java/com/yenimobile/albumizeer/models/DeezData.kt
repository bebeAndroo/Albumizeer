package com.yenimobile.albumizeer.models



data class DeezData(

    val data: List<DeezAlbum>,
    val checksum: String,
    val total: Int,
    val next: String

)


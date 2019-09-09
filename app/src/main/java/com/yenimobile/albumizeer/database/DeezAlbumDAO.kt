package com.yenimobile.albumizeer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yenimobile.albumizeer.models.DeezAlbum


@Dao
interface DeezAlbumDAO {


    @get:Query("select * from deeze_table")
    val all: List<DeezAlbum>

    @Insert
    fun insertOneAlbum(deezAlbum: DeezAlbum)

    @Insert
    fun insertAll(vararg deezAlbums: DeezAlbum)


    @Update
    fun updateOneAlbum(deezAlbum: DeezAlbum)


    @Query("Select * from deeze_table")
    fun getAllDeezAlbum(): List<DeezAlbum>


}
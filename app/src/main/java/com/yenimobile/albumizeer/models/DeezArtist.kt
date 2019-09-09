package com.yenimobile.albumizeer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "deeze_artist_table")
data class DeezArtist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "artist_id")
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "picture")
    val picture: String,

    @ColumnInfo(name = "picture_small")
    val picture_small: String,

    @ColumnInfo(name = "picture_medium")
    val picture_medium: String,

    @ColumnInfo(name = "picture_big")
    val picture_big: String,

    @ColumnInfo(name = "picture_xl")
    val picture_xl:String,

    @ColumnInfo(name = "tracklist")
    val tracklist: String,

    @ColumnInfo(name = "type_artist")
    val type: String

): Serializable





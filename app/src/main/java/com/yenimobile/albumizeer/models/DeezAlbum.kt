package com.yenimobile.albumizeer.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "deeze_table")
data class DeezAlbum(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "album_id")
    val id: Long = 0L,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "cover")
    val cover: String,

    @ColumnInfo(name = "cover_small")
    val cover_small: String,

    @ColumnInfo(name = "cover_medium")
    val cover_medium: String,

    @ColumnInfo(name = "cover_big")
    val cover_big: String,

    @ColumnInfo(name = "cover_xl")
    val cover_xl: String,

    @ColumnInfo(name = "nb_tracks")
    val nb_tracks: Int,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "record_type")
    val record_type: String,

    @ColumnInfo(name = "available")
    val available: Boolean,

    @ColumnInfo(name = "explicit_lyrics")
    val explicit_lyrics: Boolean,

    @ColumnInfo(name = "time_add")
    val time_add: Long,

    @Embedded
    val artist: DeezArtist,

    @ColumnInfo(name = "type")
    val type: String

): Serializable



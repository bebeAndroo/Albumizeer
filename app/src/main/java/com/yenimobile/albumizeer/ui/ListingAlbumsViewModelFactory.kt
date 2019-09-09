package com.yenimobile.albumizeer.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.yenimobile.albumizeer.database.DeezDatabase

class ListingAlbumsViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingalbumsViewModel::class.java)){


            //val db = DeezDatabase.getInstance(activity.applicationContext)
            val db = Room.databaseBuilder(
                activity.applicationContext,
                DeezDatabase::class.java,
                "deezwdb"
            )
                .allowMainThreadQueries()
                .build()

            @Suppress("UNCHECKED_CAST")
            return ListingalbumsViewModel(db.deezAlbumDAO(), activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
package com.yenimobile.albumizeer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yenimobile.albumizeer.models.DeezAlbum


@Database(entities = [DeezAlbum::class], version = 1, exportSchema = false)
abstract class DeezDatabase :RoomDatabase() {

    abstract fun deezAlbumDAO(): DeezAlbumDAO

    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         */
        @Volatile
        private var INSTANCE: DeezDatabase? = null

        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): DeezDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DeezDatabase::class.java,
                        "deezedb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }

}
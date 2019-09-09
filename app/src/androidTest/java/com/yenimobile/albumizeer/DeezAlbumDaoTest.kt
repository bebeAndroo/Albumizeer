package com.yenimobile.albumizeer

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.yenimobile.albumizeer.database.DeezAlbumDAO
import com.yenimobile.albumizeer.database.DeezDatabase
import com.yenimobile.albumizeer.models.DeezAlbum
import com.yenimobile.albumizeer.models.DeezArtist
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DeezAlbumDaoTest {


    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: DeezDatabase
    private lateinit var albumDao: DeezAlbumDAO

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        try {
            database = Room.inMemoryDatabaseBuilder(context, DeezDatabase::class.java)
                .allowMainThreadQueries().build()
        }catch (e: Exception) {
            Log.e("before database test" , "error : ${e.message}")
        }
        albumDao = database.deezAlbumDAO()
    }


    @Test
    fun testAddingAndRetrievingData() {

        val preInsertRetrievedALBUMS = albumDao.all
        val someArtist: DeezArtist = DeezArtist(
            0, "artistname", "artistpic", "artistpic",
            "artistpic", "artistpic", "artistpic",
            "tracklist", "type"
        )
        val somePost = DeezAlbum(1,
            "yooooo title",
            "link",
            "cover",
            "cover",
            "cover",
            "cover",
            "cover",
            0,
            "10/12/83",
            "recordtype",
            true,
            false,
            1222333,
            someArtist,
            "albunn"
        )


        albumDao.insertAll(somePost)

        val postInsertRetrievedAlbums = albumDao.all
        val sizeDiff = postInsertRetrievedAlbums.size - preInsertRetrievedALBUMS.size

        Assert.assertEquals(1, sizeDiff)

        val retreivedAlbum = postInsertRetrievedAlbums.last()
        Assert.assertEquals("yooooo title", retreivedAlbum.title)
    }


    @After
    fun tearDown() {
        database.close()
    }

}
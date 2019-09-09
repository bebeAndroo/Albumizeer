package com.yenimobile.albumizeer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yenimobile.albumizeer.ui.ListingalbumsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        startActivity(Intent(this, ListingalbumsActivity::class.java))
    }
}

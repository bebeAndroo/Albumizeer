package com.yenimobile.albumizeer

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper){
       if(context is AppCompatActivity){
           return context
       }
    }
    return null
}
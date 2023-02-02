package com.areeb.sekaisheet.utils

import android.content.Context
import android.util.Log
import android.view.View
import java.io.IOException

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun getJsonFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

    } catch (ioExceptions: IOException) {
        Log.e("sekaiSheet", ioExceptions.toString())
        return null
    }

    return jsonString

}
package com.areeb.sekaisheet.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataModelConverter @Inject constructor(@ApplicationContext val context: Context) {

    private val gson = Gson()


    fun convertCollectionList(collectionFile: String): List<com.areeb.sekaisheet.data.models.collectionsModel.Collection> {
        val collectionList = mutableListOf<com.areeb.sekaisheet.data.models.collectionsModel.Collection>()
        val jsonFileString = getJsonFromAsset(context, collectionFile)

        if (jsonFileString != null) {
            val type = object : TypeToken<List<com.areeb.sekaisheet.data.models.collectionsModel.Collection>>() {}.type
            try {
                collectionList.addAll(gson.fromJson(jsonFileString, type))
            } catch (exception: JsonParseException) {
                Log.e("TAG", exception.toString())
            } catch (exception: JsonSyntaxException) {
                Log.e("TAG", exception.toString())
            }
        }
        return collectionList.toList()
    }
}



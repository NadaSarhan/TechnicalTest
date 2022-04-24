package com.nada.technicaltest.util

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nada.technicaltest.data.entities.Item
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class JsonFileReader @Inject constructor(private val app: Application?) {

    constructor() : this(null)

    fun readFile(): ArrayList<Item> {
        var json: String? = null
        try {
            val inputStream: InputStream = app!!.assets.open("getListOfFilesResponse.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
        }
        val listDataType = object : TypeToken<ArrayList<Item>>() {}.type

        var data = ArrayList<Item>()
        data = Gson().fromJson(json, listDataType)
        return data
    }

}
package com.nada.technicaltest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nada.technicaltest.R
import com.nada.technicaltest.data.entities.Item
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ItemListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("getListOfFilesResponse.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
        }
        val listDataType = object : TypeToken<ArrayList<Item>>() {}.type

        val recyclerview = findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        var data = ArrayList<Item>()
        data = Gson().fromJson(json, listDataType)
        val adapter = ItemAdapter(data)
        recyclerview.adapter = adapter

    }

}
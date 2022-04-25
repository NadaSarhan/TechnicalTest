package com.nada.technicaltest.ui

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nada.technicaltest.R
import com.nada.technicaltest.data.entities.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ItemListViewModel by viewModels()
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        setupObservers()

//        var json: String? = null
//        try {
//            val inputStream: InputStream = assets.open("getListOfFilesResponse.json")
//            json = inputStream.bufferedReader().use { it.readText() }
//        } catch (e: IOException) {
//        }
//        val listDataType = object : TypeToken<ArrayList<Item>>() {}.type
//        var data = ArrayList<Item>()
//        data = Gson().fromJson(json, listDataType)

//        val adapter = ItemAdapter(data)
//        recyclerview.adapter = adapter

    }


    private fun setupObservers() {
        viewModel.items.observe(this, Observer {
            if(!it.equals(null)) {
                val adapter = ItemAdapter(it)
                recyclerView.adapter = adapter
            }
        })
    }

//    class FileReader @Inject constructor(private val app : Application) {
//        fun readFile(): ArrayList<Item> {
//            var json: String? = null
//            try {
//                val inputStream: InputStream = app.assets.open("getListOfFilesResponse.json")
//                json = inputStream.bufferedReader().use { it.readText() }
//            } catch (e: IOException) {
//            }
//            val listDataType = object : TypeToken<ArrayList<Item>>() {}.type
//
//            var data = ArrayList<Item>()
//            data = Gson().fromJson(json, listDataType)
////            data = Gson().fromJson(json, listDataType)
//            return data
//        }
//    }

}
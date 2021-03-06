package com.nada.technicaltest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nada.technicaltest.data.entities.Item
import com.nada.technicaltest.data.local.ItemDao
import com.nada.technicaltest.util.JsonFileReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Database(entities = [Item::class], version = 1)
abstract class AppDataBase @Inject constructor(private val coroutineScope: CoroutineScope) : RoomDatabase() {

    abstract val dao: ItemDao

//    val CALLBACK = object : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            coroutineScope.launch {
//                val list = JsonFileReader().readFile()
//                for (item in list.indices) {
//                    dao.insertItem(list[item])
//                }
//            }
//        }
//    }

    companion object {
        @Volatile private var instance: AppDataBase? = null
        lateinit var obj : CallBack
//        val callback = CallBack()

        fun getDatabase(context: Context): AppDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDataBase::class.java, "item")
                .fallbackToDestructiveMigration()
                .addCallback(obj)
                .build()

    }

    inner class CallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            coroutineScope.launch {
                val list = JsonFileReader().readFile()
                for (item in list.indices) {
                    dao.insertItem(list[item])
                }
            }
        }
    }

}
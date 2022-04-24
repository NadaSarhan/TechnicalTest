package com.nada.technicaltest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nada.technicaltest.data.entities.Item
import com.nada.technicaltest.data.local.ItemDao

@Database(
    entities = [Item::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {

    abstract val dao: ItemDao

    companion object {
        @Volatile private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDataBase::class.java, "item")
                .fallbackToDestructiveMigration()
                .build()
    }

}
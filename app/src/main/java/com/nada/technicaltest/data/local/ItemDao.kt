package com.nada.technicaltest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.nada.technicaltest.data.entities.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItemId(id: Int): Item?

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

}
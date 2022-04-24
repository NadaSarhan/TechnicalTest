package com.nada.technicaltest.data.repository

import com.nada.technicaltest.data.entities.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun getItemId(id: Int): Item?

    fun getItems(): Flow<List<Item>>

}
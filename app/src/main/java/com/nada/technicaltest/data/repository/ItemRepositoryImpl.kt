package com.nada.technicaltest.data.repository

import com.nada.technicaltest.data.entities.Item
import com.nada.technicaltest.data.local.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val dao: ItemDao
): ItemRepository {

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item)
    }
    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item)
    }
    override suspend fun getItemId(id: Int): Item? {
        return dao.getItemId(id)
    }
    override fun getItems(): Flow<List<Item>> {
        return dao.getItems()
    }

}
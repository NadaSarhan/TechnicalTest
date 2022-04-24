package com.nada.technicaltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nada.technicaltest.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    val items = repository.getItems()

}
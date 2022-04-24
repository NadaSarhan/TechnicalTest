package com.nada.technicaltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nada.technicaltest.data.repository.ItemRepository
import com.nada.technicaltest.util.UiEvent
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

//    private val _uiEvent = Channel<UiEvent>()
//    val uiEvent = _uiEvent.receiveAsFlow()
//
//    fun onEvent(event: ItemListEvent) {
//        when(event) {
//            is ItemListEvent.OnItemClick -> {
//                sendUiEvent(UiEvent.Navigate())
//            }
//        }
//    }
//
//    private fun sendUiEvent(event: UiEvent) {
//        viewModelScope.launch {
//            _uiEvent.send(event)
//        }
//    }

}
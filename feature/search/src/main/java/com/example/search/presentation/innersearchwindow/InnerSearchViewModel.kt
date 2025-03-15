package com.example.search.presentation.innersearchwindow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.data.SearchDetails
import com.example.search.domain.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InnerSearchViewModel(val searchRepository: SearchRepository): ViewModel() {

    private val _search = MutableStateFlow<SearchDetails?>(null)
    val search = _search.asStateFlow()

    fun loadSearch(page: Int) {
        viewModelScope.launch {
//            _search.value = searchRepository.getSearchDetails(keyword.value, page)
        }
    }
}
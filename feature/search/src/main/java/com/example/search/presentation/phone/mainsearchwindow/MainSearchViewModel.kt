package com.example.search.presentation.phone.mainsearchwindow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.data.modelsfordomain.SearchDetails
import com.example.search.domain.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainSearchViewModel(val searchRepository: SearchRepository): ViewModel() {

    private val _search = MutableStateFlow<SearchDetails?>(null)
    val search = _search.asStateFlow()

    private val _keyword = MutableStateFlow<String?>(null)
    val keyword = _keyword.asStateFlow()

    fun setKeyword(keyword: String) {
        viewModelScope.launch {
            _keyword.value = keyword
        }
    }

    fun loadSearch(page: Int) {
        viewModelScope.launch {
            _search.value = searchRepository.getSearchDetails(keyword.value, page)
        }
    }
}
package com.example.film.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.data.FilmDetails
import com.example.film.domain.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(val filmRepository: FilmRepository): ViewModel() {

    private val _film = MutableStateFlow<FilmDetails?>(null)
    val film = _film.asStateFlow()

    fun loadFilm(filmId: Int) {
        viewModelScope.launch {
            _film.value = filmRepository.getFilm(filmId)
        }
    }
}
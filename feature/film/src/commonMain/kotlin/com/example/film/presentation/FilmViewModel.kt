package com.example.film.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.domain.FilmDetails
import com.example.film.domain.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class FilmViewModel(val filmRepository: FilmRepository): ViewModel() {

    private val _film = MutableStateFlow<FilmDetails?>(null)
    val film = _film.asStateFlow()

    fun loadFilm(filmId: Int) {
        try {
            viewModelScope.launch {
                _film.value = filmRepository.getFilm(filmId)
            }
        } catch (e: Exception) {
            throw IOException(e.message)
        }

    }
}
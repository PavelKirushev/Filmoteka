package com.example.film.presentation

import com.example.film.domain.FilmDetails

sealed interface FilmAction {
    data object Init : FilmAction                                         // Инициализация экрана/фичи
    data object Retry : FilmAction                                       // Повторить загрузку
    data class FilmLoaded(val film: FilmDetails) : FilmAction           // Данные успешно загружены
    data class LoadError(val errorMessage: String) : FilmAction        // Ошибка загрузки
}

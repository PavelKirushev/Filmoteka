package com.example.film.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.domain.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(val filmRepository: FilmRepository): ViewModel() {

    private val mutableFilmStateFlow = MutableStateFlow<FilmViewState>(FilmViewState.Loading)
    val filmStateFlow = mutableFilmStateFlow.asStateFlow()

    private var state = FilmState()

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    fun submitAction(action: FilmAction) {
        state = applyAction(action, state)
        mutableFilmStateFlow.value = createViewState(state)

        when (action) {
            FilmAction.Init -> submitSideEffect(FilmSideEffects.LoadFilm(state.query))
            FilmAction.Retry -> {
                mutableFilmStateFlow.value = FilmViewState.Loading
                submitSideEffect(FilmSideEffects.LoadFilm(state.query))
            }
            is FilmAction.FilmLoaded,
            is FilmAction.LoadError -> Unit
        }
    }

    private fun applyAction(action: FilmAction, state: FilmState): FilmState {
        return when(action) {
            FilmAction.Init -> state.copy(isLoading = true)
            FilmAction.Retry -> state.copy(isLoading = true)
            is FilmAction.FilmLoaded -> {
                val newFilm = action.film
                state.copy(
                    film = newFilm,
                    isLoading = false
                )
            }
            is FilmAction.LoadError -> state.copy(query = action.errorMessage)
        }
    }

    private fun createViewState(state: FilmState): FilmViewState {
        return when {
            state.isLoading -> FilmViewState.Loading
            !state.error.isNullOrBlank() -> FilmViewState.Error(state.error)
            state.film != null -> FilmViewState.Film(film = state.film)
            else -> FilmViewState.Empty
        }
    }

    private fun submitSideEffect(sideEffect: FilmSideEffects) {
        when (sideEffect) {
            is FilmSideEffects.LoadFilm -> loadFilm(sideEffect.query)
        }
    }

    fun loadFilm(query: String) {
        viewModelScope.launch {
            try {
                val film = filmRepository.getFilm(query.toInt())
                submitAction(FilmAction.FilmLoaded(film))
            } catch (e: Exception) {
                submitAction(FilmAction.LoadError(e.message ?: "FATAL"))
            }
        }
    }

    fun refreshFilm() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                submitAction(FilmAction.Retry)
            } catch (e: Exception) {
                submitAction(FilmAction.LoadError(e.message ?: "FATAL"))
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}
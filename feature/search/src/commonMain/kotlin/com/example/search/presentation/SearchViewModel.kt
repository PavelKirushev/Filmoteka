package com.example.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.domain.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchRepository: SearchRepository): ViewModel() {

    private val mutableViewStateFlow = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val viewStateFlow: StateFlow<SearchViewState> = mutableViewStateFlow.asStateFlow()

    private var state: SearchState = SearchState()

    fun submitAction(action: SearchAction) {
        state = applyAction(action, state)
        mutableViewStateFlow.value = createViewState(state)

        when (action) {
            SearchAction.Init -> submitSideEffect(SearchSideEffects.LoadFilms(state.query, state.page, false))
            is SearchAction.QueryChanged -> submitSideEffect(SearchSideEffects.LoadFilms(action.query, 0, false))
            SearchAction.LoadMore -> {
                state = state.copy(isLoadingMore = true)
                submitSideEffect(SearchSideEffects.LoadFilms(state.query, state.page, true))
                state.page++
            }
            is SearchAction.FilmsLoaded,
            is SearchAction.LoadError -> Unit
        }
    }

    private fun applyAction(action: SearchAction, state: SearchState): SearchState {
        return when(action) {
            SearchAction.Init -> state.copy(isLoading = true, page = 1, canLoadMore = true)
            SearchAction.LoadMore -> {
                state.copy(isLoadingMore = true)
            }
            is SearchAction.QueryChanged -> state.copy(
                query = action.query,
                isLoading = true,
                page = 1,
                canLoadMore = true
            )
            is SearchAction.FilmsLoaded -> {
                val newItems = if (action.isLoadMore) {
                    state.items + action.films
                } else {
                    action.films
                }
                state.copy(
                    items = newItems,
                    isLoading = false,
                    isLoadingMore = false,
                    page = if (action.isLoadMore) { state.page + 1 } else { 1 },
                    canLoadMore = action.films.isNotEmpty()
                )
            }
            is SearchAction.LoadError -> state.copy(
                error = action.errorMessage,
                isLoading = false,
                isLoadingMore = false
            )
        }
    }

    private fun createViewState(state: SearchState): SearchViewState {
        return when {
            state.isLoading && !state.isLoadingMore -> SearchViewState.Loading
            !state.error.isNullOrBlank() -> SearchViewState.Error(state.error)
            else -> SearchViewState.List(
                items = state.items,
                isLoadingMore = state.isLoadingMore,
                canLoadMore = state.canLoadMore
            )
        }
    }

    private fun submitSideEffect(sideEffect: SearchSideEffects) {
        when (sideEffect) {
            is SearchSideEffects.LoadFilms -> loadFilms(sideEffect.query, sideEffect.isLoadMore)
        }
    }

    private fun loadFilms(query: String, isLoadMore: Boolean = false) {
        val pageToLoad = if (isLoadMore) { state.page } else { 1 }
        viewModelScope.launch {
            try {
                val films = withContext(Dispatchers.Default) {
                    if (query.isBlank()) {
                        println("Loading films with page: $pageToLoad")
                        searchRepository.getFilms(pageToLoad)
                    } else {
                        println("Searching films with query: $query, page: $pageToLoad")
                        searchRepository.searchFilms(query, pageToLoad)
                    }
                }
                println("Loaded ${films.films.size} films")
                submitAction(SearchAction.FilmsLoaded(films.films, isLoadMore))
            } catch (e: Exception) {
                println("Error loading films: ${e.message}")
                submitAction(SearchAction.LoadError(e.message ?: "FATAL"))
            }
        }
    }
}
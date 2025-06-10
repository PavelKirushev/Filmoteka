package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val mutableViewStateFlow = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewStateFlow: StateFlow<HomeViewState> = mutableViewStateFlow.asStateFlow()

    private var state: HomeState = HomeState()

    fun submitAction(action: HomeAction) {
        state = applyAction(action, state)
        mutableViewStateFlow.value = createViewState(state)

        when (action) {

            is HomeAction.LoadCategories -> {
                for(key in CategoryKeys.entries) {
                    submitSideEffect(HomeSideEffects.LoadCategory(key.name, 1))
                }
            }
            is HomeAction.LoadCategoryMore -> {
                val category = state.categories.find { it.query == action.query }
                if (category != null) {
                    val newPage = category.page + 1
                    val updatedCategories = state.categories.map {
                        if (it.query == action.query) it.copy(page = newPage, isLoading = true) else it
                    }
                    state = state.copy(categories = updatedCategories)
                    submitSideEffect(HomeSideEffects.LoadCategory(action.query, newPage))
                }
            }
            is HomeAction.LoadCategorySuccess,
            is HomeAction.LoadError -> Unit
        }
    }

    private fun applyAction(action: HomeAction, state: HomeState): HomeState {
        return when (action) {
            is HomeAction.LoadCategories -> {
                val categories = CategoryKeys.entries.map { key ->
                    CategoryState(query = key.name, films = emptyList(), isLoading = true, page = 1)
                }
                state.copy(categories = categories, isLoading = true, error = null)
            }
            is HomeAction.LoadCategoryMore -> {
                val updatedCategories = state.categories.map { category ->
                    if (category.query == action.query && category.canLoadMore) {
                        category.copy(isLoading = true)
                    } else {
                        category
                    }
                }
                state.copy(categories = updatedCategories)
            }
            is HomeAction.LoadCategorySuccess -> {
                val updatedCategories = state.categories.map { category ->
                    if (category.query == action.query) {
                        category.copy(
                            films = category.films + action.films,
                            isLoading = false,
                            page = category.page + 1
                        )
                    } else {
                        category
                    }
                }
                state.copy(categories = updatedCategories, isLoading = false)
            }
            is HomeAction.LoadError -> {
                state.copy(error = action.errorMessage, isLoading = false)
            }
        }
    }

    private fun createViewState(state: HomeState): HomeViewState {
        val items = state.categories.map { category ->
            CategoryDisplayItem(
                query = category.query,
                films = category.films,
                isLoading = category.isLoading,
                page = category.page,
                error = category.error,
                display = CategoryKeys.valueOf(category.query).displayItem
            )
        }
        return HomeViewState.List(items)
    }

    private fun submitSideEffect(sideEffect: HomeSideEffects) {
        when (sideEffect) {
            is HomeSideEffects.LoadCategory -> {
                loadCategory(sideEffect.type, sideEffect.page)
            }
        }
    }

    private fun loadCategory(type: String, page: Int) {
        viewModelScope.launch {
            try {
                val films = homeRepository.getCollections(type, page)
                submitAction(HomeAction.LoadCategorySuccess(query = type, films = films.films))
            } catch (e: Exception) {
                submitAction(HomeAction.LoadError(errorMessage = e.message ?: "Unknown error"))
            }
        }
    }

}
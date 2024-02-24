package com.mahesaiqbal.features.home.viewmodel

import com.mahesaiqbal.apis.movie.MovieRepository
import com.mahesaiqbal.features.home.state.HomeIntent
import com.mahesaiqbal.features.home.state.HomeState
import com.mahesaiqbal.libraries.core.state.Intent
import com.mahesaiqbal.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel<HomeState, HomeIntent>(HomeState()) {
    init {
        updateUiState {
            copy(appName = repository.getAppName())
        }
        sendIntent(HomeIntent.GetPopularMovies)
        sendIntent(HomeIntent.GetDetailMovie)
    }

    override fun sendIntent(intent: Intent) {
        when (intent) {
            is HomeIntent.GetPopularMovies -> {
                getPopularMovies()
            }
            is HomeIntent.GetDetailMovie -> {

            }
            is HomeIntent.ShowSnackbar -> {
                intent.coroutineScope.launch {
                    intent.snackbarState.showSnackbar(intent.name)
                }
            }
        }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        repository.getPopularMovies()
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncPopularMovies = it)
                }
            }
    }

    private fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        repository.getDetailMovie(movieId)
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncDetailMovie = it)
                }
            }
    }
}

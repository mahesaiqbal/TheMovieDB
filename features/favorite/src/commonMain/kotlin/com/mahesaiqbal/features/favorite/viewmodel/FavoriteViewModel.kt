package com.mahesaiqbal.features.favorite.viewmodel

import com.mahesaiqbal.apis.movie.MovieRepository
import com.mahesaiqbal.features.favorite.state.FavoriteIntent
import com.mahesaiqbal.features.favorite.state.FavoriteState
import com.mahesaiqbal.libraries.core.state.Intent
import com.mahesaiqbal.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: MovieRepository
) : ViewModel<FavoriteState, FavoriteIntent>(FavoriteState()) {

    init {
        updateUiState {
            copy(appName = repository.getAppName())
        }
        sendIntent(FavoriteIntent.GetFavoriteMovies)
    }

    override fun sendIntent(intent: Intent) {
        when (intent) {
            is FavoriteIntent.GetFavoriteMovies -> {
                getFavoriteMovies()
            }
            is FavoriteIntent.GetIsFavorite -> {
                getIsFavorite(intent.movieId)
            }
            is FavoriteIntent.ShowSnackbar -> {
                intent.coroutineScope.launch {
                    intent.snackbarState.showSnackbar(intent.name)
                }
            }
        }
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
        repository.getFavoriteMovies()
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(favoriteMovies = it)
                }
            }
    }

    private fun getIsFavorite(movieId: Int) = viewModelScope.launch {
        repository.getIsFavoriteMovie(movieId)
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(isFavorite = it)
                }
            }
    }
}

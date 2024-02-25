package com.mahesaiqbal.features.moviedetail.viewmodel

import com.mahesaiqbal.apis.movie.MovieRepository
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.moviedetail.state.MovieDetailIntent
import com.mahesaiqbal.features.moviedetail.state.MovieDetailState
import com.mahesaiqbal.libraries.core.state.Intent
import com.mahesaiqbal.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ViewModel<MovieDetailState, MovieDetailIntent>(MovieDetailState()) {
    override fun sendIntent(intent: Intent) {
        when (intent) {
            is MovieDetailIntent.GetMovieDetail -> {
                val movieId = intent.movieId
                getMovieDetail(movieId)
                getIsFavoriteMovie(movieId)
            }

            is MovieDetailIntent.ToggleFavorite -> {
                val movie = intent.movie
                toggleFavorite(movie)
            }
        }
    }

    private fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        repository.getDetailMovie(movieId)
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncMovieDetail = it)
                }
            }
    }

    private fun getIsFavoriteMovie(id: Int) = viewModelScope.launch {
        repository.getIsFavoriteMovie(id)
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(isFavorite = it)
                }
            }
    }

    private fun toggleFavorite(movie: Movie) = viewModelScope.launch {
        if (uiState.value.isFavorite) {
            repository.deleteFavorite(movie.id)
        } else {
            repository.insertFavorite(movie)
        }
    }
}

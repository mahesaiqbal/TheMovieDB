package com.mahesaiqbal.features.favorite.state

import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.libraries.core.state.Async

data class FavoriteState(
    val appName: String = "",
    val favoriteMovies: List<Movie> = emptyList(),
    val isFavorite: Boolean = false
)

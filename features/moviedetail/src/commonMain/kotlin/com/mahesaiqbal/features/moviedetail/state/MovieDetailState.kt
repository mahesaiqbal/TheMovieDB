package com.mahesaiqbal.features.moviedetail.state

import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.libraries.core.state.Async

data class MovieDetailState(
    val asyncMovieDetail: Async<Movie> = Async.Default,
    val isFavorite: Boolean = false
)

package com.mahesaiqbal.features.home.state

import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.libraries.core.state.Async

data class HomeState(
    val appName: String = "",
    val asyncPopularMovies: Async<List<Movie>> = Async.Default,
    val asyncDetailMovie: Async<Movie> = Async.Default
)

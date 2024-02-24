package com.mahesaiqbal.features.moviedetail.state

import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.libraries.core.state.Intent

sealed class MovieDetailIntent : Intent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailIntent()
    data class ToggleFavorite(val movie: Movie) : MovieDetailIntent()
}

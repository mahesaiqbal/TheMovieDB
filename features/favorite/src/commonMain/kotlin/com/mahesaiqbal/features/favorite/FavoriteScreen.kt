package com.mahesaiqbal.features.favorite

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.mahesaiqbal.apis.movie.LocalMovieRepository
import com.mahesaiqbal.apis.movie.model.movie.Movie

@Composable
fun FavoriteScreen(
    onMovieClick: (Movie) -> Unit
) {
    val movieRepository = LocalMovieRepository.current
    val scaffoldState = rememberScaffoldState()
}

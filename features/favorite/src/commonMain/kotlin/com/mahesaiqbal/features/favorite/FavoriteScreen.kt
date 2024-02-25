package com.mahesaiqbal.features.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mahesaiqbal.apis.movie.LocalMovieRepository
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.favorite.screen.FavoriteMovieSection
import com.mahesaiqbal.features.favorite.viewmodel.FavoriteViewModel
import com.mahesaiqbal.libraries.component.section.HeaderSection
import com.mahesaiqbal.libraries.core.viewmodel.rememberViewModel

@Composable
fun FavoriteScreen(
    onMovieClick: (Movie) -> Unit
) {
    val movieRepository = LocalMovieRepository.current
    val favoriteViewModel = rememberViewModel { FavoriteViewModel(movieRepository) }
    val favoriteState by favoriteViewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column {
            HeaderSection(favoriteState.appName)
            FavoriteMovieSection(
                favoriteState = favoriteState,
                onMovieClick = onMovieClick
            )
        }
    }
}

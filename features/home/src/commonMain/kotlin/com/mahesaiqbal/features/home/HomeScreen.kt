package com.mahesaiqbal.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mahesaiqbal.apis.movie.LocalMovieRepository
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.home.screen.PopularMovieSection
import com.mahesaiqbal.features.home.state.HomeIntent
import com.mahesaiqbal.features.home.viewmodel.HomeViewModel
import com.mahesaiqbal.libraries.component.section.HeaderSection
import com.mahesaiqbal.libraries.core.viewmodel.rememberViewModel

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit
) {
    val movieRepository = LocalMovieRepository.current
    val homeViewModel = rememberViewModel { HomeViewModel(movieRepository) }
    val homeState by homeViewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column {
            HeaderSection(homeState.appName)
            PopularMovieSection(
                homeState = homeState,
                onTryAgainClick = {
                    homeViewModel.sendIntent(HomeIntent.GetPopularMovies)
                },
                onMovieClick = onMovieClick
            )
        }
    }
}

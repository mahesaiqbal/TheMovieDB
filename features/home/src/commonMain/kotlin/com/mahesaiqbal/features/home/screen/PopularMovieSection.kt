package com.mahesaiqbal.features.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.home.state.HomeState
import com.mahesaiqbal.libraries.component.screen.MovieItem
import com.mahesaiqbal.libraries.component.screen.general.FailureScreen
import com.mahesaiqbal.libraries.component.screen.general.LoadingScreen
import com.mahesaiqbal.libraries.component.utils.DateFormatHelper
import com.mahesaiqbal.libraries.core.state.Async

@Composable
fun PopularMovieSection(
    modifier: Modifier = Modifier,
    homeState: HomeState,
    onTryAgainClick: () -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    val stateGrid = rememberLazyGridState()
    val mutableInteractionSource = remember { MutableInteractionSource() }

    when (val async = homeState.asyncPopularMovies) {
        is Async.Loading -> {
            LoadingScreen()
        }
        is Async.Success -> {
            LazyVerticalGrid(
                state = stateGrid,
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {
                val popularMovies = async.data
                items(popularMovies) { movie ->
                    MovieItem(
                        title = movie.title,
                        posterPath = movie.posterPath,
                        releaseDate = DateFormatHelper.getFormattedDate(movie.releaseDate),
                        modifier = modifier.clickable(
                            indication = null,
                            interactionSource = mutableInteractionSource
                        ) { onMovieClick(movie) }
                    )
                }
            }
        }
        is Async.Failure -> {
            val message = async.throwable.message.orEmpty()
            FailureScreen(message, onTryAgainClick)
        }
        else -> {}
    }
}

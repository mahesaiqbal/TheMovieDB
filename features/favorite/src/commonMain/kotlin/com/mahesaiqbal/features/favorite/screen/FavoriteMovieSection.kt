package com.mahesaiqbal.features.favorite.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.favorite.state.FavoriteState
import com.mahesaiqbal.libraries.component.screen.MovieItem
import com.mahesaiqbal.libraries.component.utils.DateFormatHelper

@Composable
fun FavoriteMovieSection(
    modifier: Modifier = Modifier,
    favoriteState: FavoriteState,
    onMovieClick: (Movie) -> Unit
) {
    val stateGrid = rememberLazyGridState()
    val mutableInteractionSource = remember { MutableInteractionSource() }

    if (favoriteState.favoriteMovies.isNotEmpty()) {
        LazyVerticalGrid(
            state = stateGrid,
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(bottom = 54.dp)
        ) {
            val favoriteMovies = favoriteState.favoriteMovies
            items(favoriteMovies) { movie ->
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
}

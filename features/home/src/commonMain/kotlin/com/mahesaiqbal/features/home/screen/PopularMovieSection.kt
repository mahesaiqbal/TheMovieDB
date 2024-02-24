package com.mahesaiqbal.features.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.home.state.HomeState
import com.mahesaiqbal.libraries.component.FailureScreen
import com.mahesaiqbal.libraries.component.LoadingScreen
import com.mahesaiqbal.libraries.component.utils.DateFormatHelper
import com.mahesaiqbal.libraries.core.state.Async
import com.seiko.imageloader.rememberImagePainter

@Composable
fun PopularMovieSection(
    modifier: Modifier = Modifier,
    homeState: HomeState,
    onTryAgainClick: () -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    val stateGrid = rememberLazyGridState()

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
                        modifier = modifier.clickable { onMovieClick(movie) }
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

@Composable
fun MovieItem(
    title: String,
    posterPath: String,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    val posterPainter = rememberImagePainter(url = "https://image.tmdb.org/t/p/w500%s$posterPath")

    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = modifier,
        content = {
            Column(
                modifier = Modifier
            ) {
                Image(
                    painter = posterPainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                    )
                )
                Text(
                    text = "Release Date: $releaseDate",
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
            }
        }
    )
}

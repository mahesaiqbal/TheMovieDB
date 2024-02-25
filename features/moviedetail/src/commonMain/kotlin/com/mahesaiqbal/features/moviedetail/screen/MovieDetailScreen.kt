package com.mahesaiqbal.features.moviedetail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.apis.movie.LocalMovieRepository
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.features.moviedetail.state.MovieDetailIntent
import com.mahesaiqbal.features.moviedetail.viewmodel.MovieDetailViewModel
import com.mahesaiqbal.libraries.component.LocalImageResource
import com.mahesaiqbal.libraries.component.screen.general.FailureScreen
import com.mahesaiqbal.libraries.component.screen.general.LoadingScreen
import com.mahesaiqbal.libraries.component.utils.DateFormatHelper
import com.mahesaiqbal.libraries.core.state.Async
import com.mahesaiqbal.libraries.core.viewmodel.rememberViewModel
import com.seiko.imageloader.rememberImagePainter

@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit = {}
) {
    val repository = LocalMovieRepository.current
    val viewModel = rememberViewModel { MovieDetailViewModel(repository) }
    val state by viewModel.uiState.collectAsState()
    val imageResources = LocalImageResource.current
    val imageFavoriteResource = if (state.isFavorite) {
        imageResources.StarFill()
    } else {
        imageResources.StarBorder()
    }
    var movieData: Movie? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        viewModel.sendIntent(MovieDetailIntent.GetMovieDetail(movieId))
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    movieData?.let { data ->
                        viewModel.sendIntent(MovieDetailIntent.ToggleFavorite(data))
                    }
                },
                contentColor = Color.White
            ) {
                Icon(
                    painter = imageFavoriteResource,
                    contentDescription = null
                )
            }
        }
    ) {
        when (val async = state.asyncMovieDetail) {
            is Async.Loading -> {
                LoadingScreen()
            }
            is Async.Success -> {
                movieData = async.data
                movieData?.let { data ->
                    MovieDetailScreenContent(
                        movie = data,
                        onBackClick = onBackClick
                    )
                }
            }
            is Async.Failure -> {
                val message = async.throwable.message.orEmpty()
                FailureScreen(message)
            }
            else -> {}
        }
    }
}

@Composable
fun MovieDetailScreenContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onBackClick: () -> Unit
) {
    val posterPainter = rememberImagePainter(
        url = "https://image.tmdb.org/t/p/original${movie.posterPath}"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = posterPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            )
            OutlinedButton(
                onClick = { onBackClick() },
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 72.dp)
        ) {
            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.Blue
                    )
                    Text(
                        text = movie.popularity.toString(),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Text(
                        text = movie.voteAverage.toString(),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color.Green
                    )
                    Text(
                        text = movie.voteCount.toString(),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.LightGray
                )
                Text(
                    text = DateFormatHelper.getFormattedDate(movie.releaseDate),
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

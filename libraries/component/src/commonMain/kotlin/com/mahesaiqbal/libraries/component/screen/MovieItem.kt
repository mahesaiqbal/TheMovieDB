package com.mahesaiqbal.libraries.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.seiko.imageloader.rememberImagePainter

@Composable
fun MovieItem(
    title: String,
    posterPath: String,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    val posterPainter = rememberImagePainter(
        url = "https://image.tmdb.org/t/p/w500$posterPath"
    )

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
                        fontSize = 12.sp
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

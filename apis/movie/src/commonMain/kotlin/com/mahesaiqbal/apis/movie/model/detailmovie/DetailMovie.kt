package com.mahesaiqbal.apis.movie.model.detailmovie

data class DetailMovie(
    val backdropPath: String,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)

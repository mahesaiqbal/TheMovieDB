package com.mahesaiqbal.apis.movie.model.movie

data class Movie(
    val backdropPath: String,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)

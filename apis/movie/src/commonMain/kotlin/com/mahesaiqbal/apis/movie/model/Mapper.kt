package com.mahesaiqbal.apis.movie.model

import com.mahesaiqbal.apis.movie.model.detailmovie.DetailMovieResponse
import com.mahesaiqbal.apis.movie.model.local.MovieRealm
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.apis.movie.model.movie.MovieResponse
import com.mahesaiqbal.apis.movie.model.movie.MovieResponse.MovieResultResponse

object Mapper {
    fun mapResponseToMovies(response: MovieResponse): List<Movie> =
        response.results.map { mapItemResponseToItemList(it) }

    private fun mapItemResponseToItemList(
        movieResponse: MovieResultResponse
    ): Movie = Movie(
        id = movieResponse.id,
        title = movieResponse.title,
        releaseDate = movieResponse.releaseDate,
        backdropPath = movieResponse.backdropPath,
        overview = movieResponse.overview,
        popularity = movieResponse.popularity,
        posterPath = movieResponse.posterPath,
        voteAverage = movieResponse.voteAverage,
        voteCount = movieResponse.voteCount
    )

    fun mapResponseToDetailMovie(
        movieResponse: DetailMovieResponse
    ): Movie = Movie(
        id = movieResponse.id,
        title = movieResponse.title,
        releaseDate = movieResponse.releaseDate,
        backdropPath = movieResponse.backdropPath,
        overview = movieResponse.overview,
        popularity = movieResponse.popularity,
        posterPath = movieResponse.posterPath,
        voteAverage = movieResponse.voteAverage,
        voteCount = movieResponse.voteCount
    )

    fun realmMapFromDetailMovie(detail: Movie): MovieRealm =
        MovieRealm()
            .apply {
                id = detail.id
                title = detail.title
                releaseDate = detail.releaseDate
                backdropPath = detail.backdropPath
                overview = detail.overview
                popularity = detail.popularity
                posterPath = detail.posterPath
                voteAverage = detail.voteAverage
                voteCount = detail.voteCount
            }

    fun realmMapToMovie(realm: MovieRealm): Movie =
        Movie(
            id = realm.id,
            title = realm.title,
            releaseDate = realm.releaseDate,
            backdropPath = realm.backdropPath,
            overview = realm.overview,
            popularity = realm.popularity,
            posterPath = realm.posterPath,
            voteAverage = realm.voteAverage,
            voteCount = realm.voteCount
        )
}

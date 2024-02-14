package com.mahesaiqbal.apis.movie

import androidx.compose.runtime.compositionLocalOf
import com.mahesaiqbal.apis.movie.model.Mapper
import com.mahesaiqbal.apis.movie.model.detailmovie.DetailMovieResponse
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.apis.movie.model.movie.MovieResponse
import com.mahesaiqbal.libraries.core.AppConfig
import com.mahesaiqbal.libraries.core.repository.Repository
import com.mahesaiqbal.libraries.core.state.Async
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val appConfig: AppConfig
) : Repository() {
    private val dataSources by lazy { MovieDataSources(appConfig) }
    private val favoriteDataSources by lazy { FavoriteMovieDataSources() }

    fun getAppName() = appConfig.appName

    fun getPopularMovies(): Flow<Async<List<Movie>>> = suspend {
        dataSources.getPopularMovies()
    }.reduce<MovieResponse, List<Movie>> { response ->
        if (response.results.isEmpty()) {
            val throwable = Throwable("Movie is empty")
            Async.Failure(throwable)
        } else {
            val data = Mapper.mapResponseToMovies(response)
            Async.Success(data)
        }
    }

    fun getDetailMovie(movieId: Int): Flow<Async<Movie>> = suspend {
        dataSources.getDetailMovie(movieId)
    }.reduce<DetailMovieResponse, Movie> { response ->
        run {
            val data = Mapper.mapResponseToDetailMovie(response)
            Async.Success(data)
        }
    }

    suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return favoriteDataSources.getFavoriteMovies()
    }

    suspend fun getIsFavoriteMovie(movieId: Int): Flow<Boolean> {
        return favoriteDataSources.getIsFavoriteMovie(movieId)
    }

    suspend fun insertFavoriteMovie(movie: Movie) {
        favoriteDataSources.insertMovie(movie)
    }

    suspend fun deleteFavoriteMovie(movieId: Int) {
        favoriteDataSources.removeMovie(movieId)
    }
}

val LocalMovieRepository = compositionLocalOf<MovieRepository> {
    error("MovieRepository not provided!")
}

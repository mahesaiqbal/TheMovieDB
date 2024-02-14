package com.mahesaiqbal.apis.movie

import com.mahesaiqbal.apis.movie.model.Mapper
import com.mahesaiqbal.apis.movie.model.local.MovieRealm
import com.mahesaiqbal.apis.movie.model.movie.Movie
import com.mahesaiqbal.libraries.core.local.LocalDataSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieDataSources : LocalDataSources(MovieRealm::class) {

    suspend fun insertMovie(detail: Movie) {
        val realm = Mapper.realmMapFromDetailMovie(detail)
        insertObject(realm)
    }

    suspend fun removeMovie(movieId: Int) {
        removeObject(MovieRealm::class, movieId)
    }

    suspend fun getIsFavoriteMovie(movieId: Int): Flow<Boolean> =
        getObjectExistById(MovieRealm::class, movieId)

    suspend fun getFavoriteMovies(): Flow<List<Movie>> =
        getObjects(MovieRealm::class)
            .map {
                it.map {
                    Mapper.realmMapToMovie(it)
                }
            }
}

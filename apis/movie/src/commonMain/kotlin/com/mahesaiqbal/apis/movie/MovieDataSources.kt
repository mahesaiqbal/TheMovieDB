package com.mahesaiqbal.apis.movie

import com.mahesaiqbal.libraries.core.AppConfig
import com.mahesaiqbal.libraries.core.network.NetworkDataSources
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.delay

class MovieDataSources(
    private val appConfig: AppConfig
) : NetworkDataSources(appConfig.baseUrl) {
    suspend fun getPopularMovies(): HttpResponse {
        val endpoint = "movie/popular?api_key=${appConfig.apiKey}"
        delay(1000)
        return getHttpResponse(endpoint)
    }
    suspend fun getDetailMovie(movieId: Int): HttpResponse {
        val endpoint = "movie/$movieId?api_key=${appConfig.apiKey}"
        delay(1000)
        return getHttpResponse(endpoint)
    }
}
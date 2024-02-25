package com.mahesaiqbal.features.favorite.state

import androidx.compose.material.SnackbarHostState
import com.mahesaiqbal.libraries.core.state.Intent
import kotlinx.coroutines.CoroutineScope

sealed class FavoriteIntent : Intent {
    data object GetFavoriteMovies : FavoriteIntent()
    data class GetIsFavorite(
        val movieId: Int
    ) : FavoriteIntent()
    data class ShowSnackbar(
        val name: String,
        val snackbarState: SnackbarHostState,
        val coroutineScope: CoroutineScope
    ) : FavoriteIntent()
}

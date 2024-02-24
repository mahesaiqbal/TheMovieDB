package com.mahesaiqbal.features.home.state

import androidx.compose.material.SnackbarHostState
import com.mahesaiqbal.libraries.core.state.Intent
import kotlinx.coroutines.CoroutineScope

sealed class HomeIntent : Intent {
    data object GetPopularMovies : HomeIntent()
    data object GetDetailMovie : HomeIntent()
    data class ShowSnackbar(
        val name: String,
        val snackbarState: SnackbarHostState,
        val coroutineScope: CoroutineScope
    ) : HomeIntent()
}

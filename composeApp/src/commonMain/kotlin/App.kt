import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mahesaiqbal.apis.movie.LocalMovieRepository
import com.mahesaiqbal.apis.movie.MovieRepository
import com.mahesaiqbal.features.moviedetail.screen.MovieDetailScreen
import com.mahesaiqbal.libraries.component.LocalImageResource
import com.mahesaiqbal.libraries.core.LocalAppConfig
import com.mahesaiqbal.libraries.core.viewmodel.LocalViewModelHost
import com.mahesaiqbal.libraries.core.viewmodel.ViewModelHost
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModelHost = remember { ViewModelHost() }
    val appConfigProvider = remember { AppConfigProvider() }
    val movieRepository = remember { MovieRepository(appConfigProvider) }
    val imageResourcesProvider = remember { ImageResourcesProvider() }
    val tabNavigator = remember { TabNavigator() }

    CompositionLocalProvider(
        LocalViewModelHost provides viewModelHost,
        LocalAppConfig provides appConfigProvider,
        LocalMovieRepository provides movieRepository,
        LocalImageResource provides imageResourcesProvider,
        LocalTabNavigator provides tabNavigator
    ) {
        MaterialTheme {
            PreComposeApp {
                val navigator = rememberNavigator()
                NavHost(
                    navigator = navigator,
                    navTransition = NavTransition(),
                    initialRoute = "/home"
                ) {
                    scene(
                        route = "/home"
                    ) {
                        PagerScreen(navigator)
                    }
                    scene(
                        route = "/detail/{id}"
                    ) {
                        val movieId = it.pathMap["id"].orEmpty().toIntOrNull() ?: 0
                        MovieDetailScreen(
                            movieId = movieId,
                            onBackClick = { navigator.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

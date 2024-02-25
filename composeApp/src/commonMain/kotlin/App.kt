import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
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

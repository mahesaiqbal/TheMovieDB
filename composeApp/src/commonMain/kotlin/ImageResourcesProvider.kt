import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.mahesaiqbal.libraries.component.utils.ImageResources
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
class ImageResourcesProvider : ImageResources {

    @Composable
    override fun ArrowBack(): Painter =
        painterResource("round_arrow_back_24.xml")

    @Composable
    override fun StarFill(): Painter =
        painterResource("round_star_24.xml")

    @Composable
    override fun StarBorder(): Painter =
        painterResource("round_star_border_24.xml")
}

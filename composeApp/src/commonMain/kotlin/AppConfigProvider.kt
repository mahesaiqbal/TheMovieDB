import com.mahesaiqbal.app.BuildKonfig
import com.mahesaiqbal.libraries.core.AppConfig

class AppConfigProvider : AppConfig {
    override val appName: String
        get() = BuildKonfig.APP_NAME
    override val baseUrl: String
        get() = BuildKonfig.BASE_URL
    override val apiKey: String
        get() = BuildKonfig.API_KEY
}

package itapeviprev.cursoandroid.com.itapeviprev

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import itapeviprev.cursoandroid.com.itapeviprev.theme.ItapeviPrevTheme
import dagger.hilt.android.AndroidEntryPoint
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.boardNavigation
import itapeviprev.cursoandroid.com.itapeviprev.navigation.AppNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.navigation.appNavigation
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val isReady = mutableStateOf(false)
        Timer().schedule(3000) { isReady.value = true }

        installSplashScreen().apply {
            setKeepOnScreenCondition { !isReady.value }
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContent {
            val navController = rememberNavController()
            ItapeviPrevTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController,
                        startDestination = if (viewModel.userIsLoggedIn.value) BoardNavigationScreens.BoardRoute.name else AppNavigationScreens.AppNavigationRoute.name
                    ) {
                        appNavigation(navController)
                        boardNavigation(navController)
                    }
                }
            }
        }
    }
}
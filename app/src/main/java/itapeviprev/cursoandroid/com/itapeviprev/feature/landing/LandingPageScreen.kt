package itapeviprev.cursoandroid.com.itapeviprev.feature.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.feature.landing.widgets.ButtonsContainer
import itapeviprev.cursoandroid.com.itapeviprev.feature.landing.widgets.BannerSliderCard
import itapeviprev.cursoandroid.com.itapeviprev.navigation.AppNavigationScreens


@Composable
fun LandingPageScreen(navController: NavHostController) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top) {
            Column(modifier = Modifier.fillMaxSize()) {
                BannerSliderCard()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                    verticalArrangement = Arrangement.Center) {
                    ButtonsContainer(
                        onLoginClick = {navController.navigate(AppNavigationScreens.LoginScreen.name)},
                        onCreateAccountClick = {navController.navigate(AppNavigationScreens.CreateAccountScreen.name)})
                }
            }
        }
    }
}
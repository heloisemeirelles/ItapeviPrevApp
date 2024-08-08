package itapeviprev.cursoandroid.com.itapeviprev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.BoardScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.landing.LandingPageScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui.LoginScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.CreateAccountScreen

@Composable
fun AppNavigation(navController: NavHostController, userIsLoggedIn: Boolean) {
    NavHost(navController = navController,
        startDestination = if(userIsLoggedIn) AppNavigationScreens.BoardScreen.name else AppNavigationScreens.LandingPageScreen.name) {
        composable(route = AppNavigationScreens.LandingPageScreen.name) {
            LandingPageScreen(navController)
        }

        composable(route = AppNavigationScreens.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(route = AppNavigationScreens.BoardScreen.name) {
            BoardScreen()
        }

        composable(route = AppNavigationScreens.CreateAccountScreen.name) {
            CreateAccountScreen(navController)
        }
    }
}
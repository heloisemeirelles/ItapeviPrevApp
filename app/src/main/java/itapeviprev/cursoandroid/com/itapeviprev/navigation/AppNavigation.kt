package itapeviprev.cursoandroid.com.itapeviprev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

import itapeviprev.cursoandroid.com.itapeviprev.feature.landing.LandingPageScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui.LoginScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.CreateAccountScreen

fun NavGraphBuilder.appNavigation(navController: NavHostController) {
    navigation(
        startDestination = AppNavigationScreens.LandingPageScreen.name,
        route = AppNavigationScreens.AppNavigationRoute.name
    ) {
        composable(route = AppNavigationScreens.LandingPageScreen.name) {
            LandingPageScreen(navController)
        }

        composable(route = AppNavigationScreens.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(route = AppNavigationScreens.CreateAccountScreen.name) {
            CreateAccountScreen(navController)
        }
    }
}
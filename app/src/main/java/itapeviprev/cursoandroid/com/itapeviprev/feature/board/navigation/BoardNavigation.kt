package itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import itapeviprev.cursoandroid.com.itapeviprev.PaymentForecastScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.BoardScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.ContributionSimulatorScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.importantAnnouncement.ImportantAnnouncementScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.BenefitScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.BenefitsListScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.contact.ContactScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.councilScreen.CouncilScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fundHealthScreen.FundHealthScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.FundScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.PaymentsInfoScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.pdfLcl.PdfLclScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfoScreen.ProcessInfoScreen

fun NavGraphBuilder.boardNavigation(navController: NavHostController) {
    navigation(
        startDestination = BoardNavigationScreens.BoardScreen.name,
        route = BoardNavigationScreens.BoardRoute.name
    ) {
        composable(route = BoardNavigationScreens.BoardScreen.name) {
            BoardScreen(navController)
        }

        composable(route = BoardNavigationScreens.BenefitsListScreen.name) {
            BenefitsListScreen(navController)
        }

        composable(route = BoardNavigationScreens.PdfLclScreen.name) {
            PdfLclScreen()
        }

        composable(route = BoardNavigationScreens.PaymentsInfoScreen.name) {
            PaymentsInfoScreen(navController)
        }

        composable(route = BoardNavigationScreens.FundHealthScreen.name) {
            FundHealthScreen()
        }

        composable(route = BoardNavigationScreens.FundScreen.name) {
            FundScreen()
        }

        composable(route = BoardNavigationScreens.ProcessInfoScreen.name) {
            ProcessInfoScreen()
        }

        composable(route = BoardNavigationScreens.CouncilScreen.name) {
            CouncilScreen()
        }

        composable(route = BoardNavigationScreens.ContactScreen.name) {
            ContactScreen()
        }

        composable(route = BoardNavigationScreens.ContributionSimulatorScreen.name) {
            ContributionSimulatorScreen(navController = navController)
        }

        composable(route = BoardNavigationScreens.ImportantAnnouncementScreen.name) {
            ImportantAnnouncementScreen(navController)
        }

        composable(route = BoardNavigationScreens.BenefitScreen.name + "/{benefitType}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("benefitType")
            BenefitScreen(navController, benefitType = message.toString())
        }

        composable(route = BoardNavigationScreens.PaymentForecastScreen.name) {
            PaymentForecastScreen(navController = navController)
        }
    }
}

package itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.CouncilScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.CandidatesScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.documentsToApply.DocumentsToApplyScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fundHealthScreen.FundHealthScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.FundScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.fundHelper.FundHelperScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.PaymentsInfoScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.pdfLcl.PdfLclScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfoScreen.ProcessInfoScreen
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
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
            FundScreen(navController)
        }

        composable(route = BoardNavigationScreens.ProcessInfoScreen.name) {
            ProcessInfoScreen()
        }

        composable(route = BoardNavigationScreens.CouncilScreen.name) {
            CouncilScreen(navController)
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

        composable(route = BoardNavigationScreens.FundHelperScreen.name) {
            FundHelperScreen(navController)
        }

        composable(route = BoardNavigationScreens.ProfileScreen.name) {
            ProfileScreen(navController)
        }

        composable(route = BoardNavigationScreens.CandidatesScreen.name) {
            CandidatesScreen(navController)
        }

        composable(route = BoardNavigationScreens.DocumentsToApplyScreen.name) {
            DocumentsToApplyScreen(navController)
        }
    }
}

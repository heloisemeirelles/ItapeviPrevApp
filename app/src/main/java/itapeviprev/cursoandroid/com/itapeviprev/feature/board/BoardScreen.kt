package itapeviprev.cursoandroid.com.itapeviprev.feature.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.viewModel.BoardViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets.CardGrid
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithIconAndText

@Composable
fun BoardScreen(
    navController: NavHostController,
    viewModel: BoardViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                if (viewModel.userIsLoggedIn.value) {
                    HeaderWithIconAndText(
                        text = viewModel.getUserEmail(),
                        icon = Icons.Outlined.Person
                    ) {
                        navController.navigate(BoardNavigationScreens.ProfileScreen.name)

                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
            ) {

                CardGrid(
                    viewModel.getCardInfo { route -> navController.navigate(route) },
                    viewModel.getSliderInfo { route -> navController.navigate(route) })
            }
        }
    }
}


package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.viewModel.CouncilViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.widgets.CandidateCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.widgets.CouncilOptions
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon
import androidx.activity.compose.BackHandler
import androidx.compose.ui.graphics.Color
import itapeviprev.cursoandroid.com.itapeviprev.widgets.WebViewScreen

@Composable
fun CouncilScreen(
    navController: NavHostController,
    viewModel: CouncilViewModel = hiltViewModel()
) {
    val currentWebViewUrl = remember { mutableStateOf<Int?>(null) }
    val showWebView = remember { mutableStateOf(false) }

    BackHandler {
        showWebView.value = false
        currentWebViewUrl.value = null
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (showWebView.value) {
                currentWebViewUrl.value?.let { stringResource(id = it) }?.let { WebViewScreen(it) }
            }
            HeaderWithImageAndIcon(imageResId = R.drawable.img_council_header, iconTint = Color.White) {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.council),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = stringResource(id = R.string.get_to_know_the_candidates),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))

                CandidateCard { navController.navigate(BoardNavigationScreens.CandidatesScreen.name) }

                Divider(modifier = Modifier.padding(top = 24.dp), color = PrimaryLightGray)

                Text(
                    text = stringResource(id = R.string.other_actions),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 24.dp),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.size(16.dp))

                viewModel.getCouncilOptions().forEach { option ->
                    CouncilOptions(option) {
                        if(option.titleId == R.string.documents_to_apply) {
                            navController.navigate(BoardNavigationScreens.DocumentsToApplyScreen.name)
                        } else {
                            showWebView.value = true
                            currentWebViewUrl.value = option.urlId
                        }
                    }
                    if (option != viewModel.getCouncilOptions().last()) {
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}




package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.viewModel.BenefitsListViewModel
import itapeviprev.cursoandroid.com.itapeviprev.widgets.GenericActionCard
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithOneIcon

@Composable
fun BenefitsListScreen(
    navController: NavHostController,
    viewModel: BenefitsListViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderWithOneIcon(icon = painterResource(id = R.drawable.ic_back_arrow)) {
                    navController.popBackStack()
                }
                androidx.compose.material3.Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                    text = stringResource(id = R.string.benefits),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                viewModel.getBenefitsList().forEach { item ->
                    GenericActionCard(item.titleId, item.imageResId) {
                        navController.navigate(item.route)
                    }
                    if (item != viewModel.getBenefitsList().last()) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
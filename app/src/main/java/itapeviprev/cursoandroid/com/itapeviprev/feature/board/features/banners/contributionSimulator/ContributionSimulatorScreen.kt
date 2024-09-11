package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.viewModel.ContributionSimulatorViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.widgets.ContributionSimulatorBody
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.widgets.SimulationCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.widgets.BannerHeader
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent24
import itapeviprev.cursoandroid.com.itapeviprev.widgets.decimalFormat

@Composable
fun ContributionSimulatorScreen(
    viewModel: ContributionSimulatorViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            BannerHeader(
                imgId = R.drawable.img_resized_simulator,
                backgroundColor = PrimaryBlue
            ) {
                navController.popBackStack()
            }
            if (viewModel.showSimulation.value) {
                Column(modifier = Modifier.padding(24.dp)) {
                    LazyColumn(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        item {
                            viewModel.getListOfSimulationValues().forEach { item ->
                                SimulationCard(item)
                                if (item != viewModel.getListOfSimulationValues().last()) {
                                    Spacer(modifier = Modifier.size(8.dp))
                                }
                            }
                            Spacer(modifier = Modifier.size(24.dp))
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(PrimaryBlue, shape = RoundedCornerShape(8.dp, 8.dp))
                                    .clip(RoundedCornerShape(8.dp, 8.dp))
                                    .padding(24.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                viewModel.getListOfTableTitle()
                                    .forEach { textId -> ColumnText(textId) }
                            }
                        }
                        items(viewModel.getListOfTableValues()) { item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .background(PrimaryLightGrayTransparent24)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .weight(4.5f),
                                        text = stringResource(id = item.salaryId),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        modifier = Modifier
                                            .weight(9f),
                                        textAlign = TextAlign.Center,
                                        text = stringResource(id = item.taxRateValue),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        modifier = Modifier
                                            .weight(3f),
                                        textAlign = TextAlign.Center,
                                        text = decimalFormat(item.value),
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                }
                                if (item != viewModel.getListOfTableValues().last()) {
                                    Divider(color = PrimaryGray)
                                }

                            }

                        }
                    }

                }
            } else {
                ContributionSimulatorBody(viewModel.valueToSimulate, viewModel.showSimulation, viewModel.isButtonEnabled())
            }

        }
    }
}

@Composable
fun ColumnText(textId: Int) {
    Text(
        text = stringResource(id = textId),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun ContributionSimulatorScreenPreview() {
    val navController = rememberNavController()
    ContributionSimulatorScreen(navController = navController)
}
package itapeviprev.cursoandroid.com.itapeviprev

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.LawInformative
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.PaymentForecastCardSlider
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.PaymentForecastState
import itapeviprev.cursoandroid.com.itapeviprev.widgets.CustomCircularProgressBar
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ErrorDialog
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon
import kotlin.system.exitProcess

@Composable
fun PaymentForecastScreen(
    viewModel: PaymentForecastViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val showErrorDialog = remember { mutableStateOf(false) }
    val paymentForecastState by viewModel.paymentForecastState.collectAsState(initial = true)

    LaunchedEffect(Unit) {
        viewModel.fetchPaymentData()

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderWithImageAndIcon(imageResId = R.drawable.img_payment_forecast) {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when (paymentForecastState) {
                    is PaymentForecastState.Loading -> CustomCircularProgressBar()

                    is PaymentForecastState.Complete -> {
                        showErrorDialog.value = false
                        Text(
                            text = stringResource(id = R.string.payment_forecast_for_ITAPEVIPREV_recipients),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id = R.string.follow_payment_forecast),
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        Spacer(modifier = Modifier.size(24.dp))
                        LawInformative()
                        Spacer(modifier = Modifier.size(24.dp))
                        PaymentForecastCardSlider(viewModel.paymentList)
                    }

                    is PaymentForecastState.Error -> {
                        showErrorDialog.value = true
                    }

                    else -> {}

                }

                if (showErrorDialog.value) {
                    ErrorDialog(onDismissClick = {
                        viewModel.refreshState()
                        showErrorDialog.value = false
                    },
                        onTryAgain = {
                            showErrorDialog.value = false
                            viewModel.refreshState()
                        }) {
                        exitProcess(0)
                    }
                }
            }
        }
    }
}
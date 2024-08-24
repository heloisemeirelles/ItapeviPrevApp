package itapeviprev.cursoandroid.com.itapeviprev

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.LawInformative
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.PaymentForecastCardSlider
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.PaymentForecastState
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon

@Composable
fun PaymentForecastScreen(
    viewModel: PaymentForecastViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val paymentForecastState by viewModel.paymentForecastState.collectAsState(initial = true)

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
                    is PaymentForecastState.Initial -> viewModel.fetchPaymentData()
                    is PaymentForecastState.Loading -> CircularProgressIndicator(
                        color = PrimaryBlue,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    is PaymentForecastState.Complete -> {
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
                }
            }
        }
    }
}
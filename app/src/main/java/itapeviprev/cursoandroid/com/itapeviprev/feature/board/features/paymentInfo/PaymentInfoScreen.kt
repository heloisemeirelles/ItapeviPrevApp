package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.widgets.GenericActionCard
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon

@Composable
fun PaymentsInfoScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val payStubUri = Uri.parse(stringResource(id = R.string.pay_stub_url))

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderWithImageAndIcon(
                imageResId = R.drawable.img_simulation_header,
                onClick = { navController.popBackStack() })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = stringResource(id = R.string.payment_infos),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                GenericActionCard(
                    titleId = R.string.online_pay_stub,
                    imageResId = R.drawable.img_pay_stub
                ) {
                    openPayStubWebPage(payStubUri, context)
                }

                Spacer(modifier = Modifier.size(16.dp))

                GenericActionCard(
                    titleId = R.string.payment_forecast_for_ITAPEVIPREV_recipients,
                    imageResId = R.drawable.img_payment_forecast,
                ) {
                    navController.navigate(BoardNavigationScreens.PaymentForecastScreen.name)
                }
            }
        }
    }
}

private fun openPayStubWebPage(uri: Uri?, context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        uri
    )
    context.startActivity(intent)
}
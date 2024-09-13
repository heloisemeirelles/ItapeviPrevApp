package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import itapeviprev.cursoandroid.com.itapeviprev.FirebasePaymentModel
import itapeviprev.cursoandroid.com.itapeviprev.PaymentSituation
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.BlueDark
import itapeviprev.cursoandroid.com.itapeviprev.theme.Green
import itapeviprev.cursoandroid.com.itapeviprev.theme.LightGreen
import itapeviprev.cursoandroid.com.itapeviprev.theme.OrangeLight
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryRed
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryYellow
import itapeviprev.cursoandroid.com.itapeviprev.theme.RedLight
import itapeviprev.cursoandroid.com.itapeviprev.theme.YellowLight

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PaymentForecastCardSlider(paymentList: MutableList<MutableList<FirebasePaymentModel?>>) {
    val pagerState = rememberPagerState(initialPage = 0)
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        HorizontalPager(
            state = pagerState,
            count = paymentList.size,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) { page ->
            PaymentForecastCard(page, paymentList)
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Composable
fun PaymentForecastCard(page: Int, paymentList: MutableList<MutableList<FirebasePaymentModel?>>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = PrimaryLightGrayTransparent,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(BlueDark)
            ) {
                Text(
                    text = stringResource(id = getPaymentCardTitleId(page)),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            paymentList[page].forEach { item ->
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    item?.mês?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    item?.dia?.let { PaymentDate(it) }

                    item?.situacao?.let { PaymentStatus(it) }

                    if (item !== paymentList[page].last()) {
                        Divider(modifier = Modifier.padding(top = 16.dp), color = PrimaryGray)
                    }
                }
            }
        }
    }
}

fun getPaymentCardTitleId(cardNumber: Int): Int {
    return when (cardNumber) {
        0 -> R.string.january_to_march
        1 -> R.string.april_to_june
        2 -> R.string.july_to_september
        else -> R.string.october_to_december
    }
}

@Composable
private fun PaymentStatus(status: String) {
    val statusSettings = PaymentStatusSettings(status).getPaymentSettings()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_progress),
            contentDescription = "",
            tint = PrimaryBlack
        )
        Text(
            text = stringResource(id = R.string.payment_status),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 4.dp)
        )
    }

    Spacer(modifier = Modifier.size(4.dp))

    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(CircleShape)
            .border(1.dp, statusSettings.borderColor, shape = CircleShape)
            .background(shape = CircleShape, color = statusSettings.backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .padding(4.dp)
                .size(13.dp),
            painter = painterResource(id = statusSettings.iconId),
            contentDescription = ""
        )

        Text(
            text = status,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(4.dp, end = 8.dp)
        )
    }
}

@Composable
private fun PaymentDate(date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "",
            tint = PrimaryBlack
        )
        Text(
            text = stringResource(id = R.string.payment_date),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 4.dp)
        )

    }

    Text(
        text = date,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Preview
@Composable
fun LawInformative() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(color = OrangeLight, shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Outlined.Info,
            contentDescription = "",
            tint = PrimaryBlack
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.law_art_93),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.beneficiaries_are_paid_monthly),
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 14.sp
            )
        }
    }
}

@Preview
@Composable

fun PaymentForecastCardPreview() {
    val list = mutableListOf(
        mutableListOf<FirebasePaymentModel?>(FirebasePaymentModel(
            "Janeiro",
            "02 de fevereiro",
            "PAGO"
        ))
    )
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            PaymentForecastCardSlider(list)
        }
    }
}

data class PaymentStatusSettings(
    val status: String
) {
    fun getPaymentSettings(): PaymentStatusCardInfo {
        return when (status) {
            PaymentSituation.PAID -> {
                PaymentStatusCardInfo(
                    backgroundColor = LightGreen,
                    borderColor = Green,
                    iconId = R.drawable.ic_rounded_check
                )
            }

            PaymentSituation.IN_CLOSING -> {
                PaymentStatusCardInfo(
                    backgroundColor = YellowLight,
                    borderColor = PrimaryYellow,
                    iconId = R.drawable.ic_refresh
                )
            }

            else -> {
                PaymentStatusCardInfo(
                    backgroundColor = RedLight,
                    borderColor = PrimaryRed,
                    iconId = R.drawable.ic_pending
                )
            }
        }
    }
}

data class PaymentStatusCardInfo(
    val backgroundColor: Color,
    val borderColor: Color,
    val iconId: Int
)



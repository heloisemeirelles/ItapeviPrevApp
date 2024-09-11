package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.contributionSimulator.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.widgets.BannerHeader
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent24
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedTextField

@Composable
fun ContributionSimulatorBody(text: MutableState<String>, showSimulation: MutableState<Boolean>, isEnabled: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = stringResource(id = R.string.contribution_simulator),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(id = R.string.progressive_tax_rate),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
            Divider(color = PrimaryLightGray)
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = getBoldString(), style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = stringResource(id = R.string.contribution_value),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(8.dp))
            RoundedTextField(
                leadingIconId = R.drawable.ic_dolar_sign,
                text = text,
                placeholder = stringResource(id = R.string.add_contribution_value),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
        RoundedButton(
            enabled = isEnabled, backgroundColor = PrimaryBlue, labelColor = Color.White, label = stringResource(
                id = R.string.simulate
            )
        ) {
            showSimulation.value = true
        }
    }
}

@Composable
fun getBoldString(): AnnotatedString {
    val boldString = stringResource(id = R.string.insert_total_value).substringAfter("%s1")
        .substringBefore("%s1")
    val entireString = stringResource(id = R.string.insert_total_value).split("%s1")[0]
    return buildAnnotatedString {
        append(entireString)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(boldString)
        }
        append(".")
    }
}

@Composable
fun SimulationCard(item: SimulationCardModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryLightGrayTransparent24)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier.padding(end = 24.dp),
                painter = painterResource(id = item.iconId), contentDescription = ""
            )
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = item.titleId),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = item.value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}

data class SimulationCardModel(
    val titleId: Int,
    val iconId: Int,
    val value: String
)
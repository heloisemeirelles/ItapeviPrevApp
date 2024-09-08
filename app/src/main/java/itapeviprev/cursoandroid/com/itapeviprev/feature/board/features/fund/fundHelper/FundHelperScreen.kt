package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.fundHelper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithOneIcon

@Composable
fun FundHelperScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderWithOneIcon(icon = painterResource(id = R.drawable.ic_back_arrow)) {
                navController.popBackStack()
            }
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.worth_to_know),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.whats_the_regime),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(id = R.string.regime_text),
                    style = MaterialTheme.typography.bodyLarge
                )

                Divider(
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                    color = PrimaryLightGray
                )
                Text(
                    text = stringResource(id = R.string.contribution_for_itapeviprev),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(id = R.string.contribution_for_itapeviprev_text),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
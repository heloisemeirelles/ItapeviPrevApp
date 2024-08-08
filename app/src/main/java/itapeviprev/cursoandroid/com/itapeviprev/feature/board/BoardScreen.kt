package itapeviprev.cursoandroid.com.itapeviprev.feature.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets.BoardOptionCards
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets.BoardSlider
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithBackButtonAndLogo
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithTwoIcons
import java.security.PrivateKey

@Composable
fun BoardScreen(viewModel: BoardViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 16.dp)
            ) {
                HeaderWithTwoIcons(
                    arrangement = Arrangement.SpaceBetween,
                    firstIconClick = {},
                    secondIconClick = {},
                    secondIcon = painterResource(id = R.drawable.ic_profile)
                )
            }

            BoardSlider()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Spacer(modifier = Modifier.size(32.dp))
                Text(
                    text = stringResource(id = R.string.actions_pane),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.size(16.dp))
                
            }

        }
    }
}
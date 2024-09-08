package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model.FundCardTypes
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.viewModel.FundViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.FundInfoCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.OpeningHoursCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray

@Composable
fun FundScreen(navController: NavController,
               viewModel: FundViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            Header { navController.popBackStack() }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(modifier = Modifier.padding(end = 16.dp), text = stringResource(id = R.string.about_the_itapeviprev),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold)
                    Box(modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .background(
                            PrimaryBlue, shape = CircleShape
                        )
                        .clickable {
                            navController.navigate(BoardNavigationScreens.FundHelperScreen.name)
                        }
                        .padding(8.dp)) {
                        Image(painter = painterResource(id = R.drawable.ic_helper), contentDescription = "")
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))

                FundCardTypes.entries.forEach {
                    FundInfoCard(viewModel.getFundCardInfo(it))
                    Spacer(modifier = Modifier.size(16.dp))
                }

                Divider(color = PrimaryGray)

                Text(modifier = Modifier.padding(top = 16.dp), text = stringResource(id = R.string.location_and_contact),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold)

                OpeningHoursCard()

                viewModel.getListOfContacts(context).forEach {item ->
                    ContactCard(item)
                    Spacer(modifier = Modifier.size(16.dp))
                }

            }
        }
    }
}

@Composable
private fun Header(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryBlue)
            .height(160.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(modifier = Modifier.padding(24.dp), onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_itapevi_logo_white),
                    contentDescription = ""
                )
            }
        }
    }
}
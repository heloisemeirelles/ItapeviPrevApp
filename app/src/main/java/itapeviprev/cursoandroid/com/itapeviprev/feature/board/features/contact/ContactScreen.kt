package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.contact

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.contact.viewModel.ContactViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactCard
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon

@Composable
fun ContactScreen(
    navController: NavHostController,
    viewModel: ContactViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HeaderWithImageAndIcon(
                imageResId = R.drawable.img_header_contact,
                iconTint = Color.White,
            ) {
                navController.popBackStack()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.our_contacts),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(24.dp))

                viewModel.getListOfContacts(context).forEach { item ->
                    ContactCard(item)
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
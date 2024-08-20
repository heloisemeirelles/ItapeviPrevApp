package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.importantAnnouncement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.importantAnnouncement.viewModel.ImportantAnnouncementViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.widgets.BannerHeader
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlueDark
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent24

@Composable
fun ImportantAnnouncementScreen(
    navController: NavHostController,
    viewModel: ImportantAnnouncementViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            BannerHeader(
                imgId = R.drawable.img_resized_announcement,
                backgroundColor = PrimaryBlueDark
            ) {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.attention_retiree),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,

                    )

                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .background(PrimaryLightGrayTransparent24, shape = RoundedCornerShape(8.dp))

                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        viewModel.getListOfTextBlocks().forEach { textBlock ->
                            TextBlocksComponent(
                                textBlock.titleId,
                                textBlock.subtitleId
                            )
                            if (textBlock != viewModel.getListOfTextBlocks().last()) {
                                Spacer(modifier = Modifier.size(24.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextBlocksComponent(titleId: Int, subtitleId: Int) {
    Text(
        text = stringResource(id = titleId),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = stringResource(id = subtitleId),
        style = MaterialTheme.typography.bodyMedium
    )
}

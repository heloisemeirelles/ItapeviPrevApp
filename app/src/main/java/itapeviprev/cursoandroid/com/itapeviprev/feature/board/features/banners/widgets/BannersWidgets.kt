package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue

@Composable
fun BannerHeader(imgId: Int, backgroundColor: Color, onBackButtonClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(modifier = Modifier.padding(top = 40.dp, start = 24.dp),
            onClick = { onBackButtonClick() }) {
            Icon(
                tint = Color.White,
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = ""
            )
        }
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = imgId),
            contentDescription = ""
        )
    }
}
package itapeviprev.cursoandroid.com.itapeviprev.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray

@Composable
fun HeaderWithOneIcon(
    arrangement: Arrangement.Horizontal = Arrangement.Start,
    icon: Painter,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(painter = icon, contentDescription = "")
        }

    }
}

@Composable
fun HeaderWithTwoIcons(
    arrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    firstIcon: Painter = painterResource(id = R.drawable.ic_back_arrow),
    secondIcon: Painter,
    showFirstIcon: Boolean = true,
    showSecondIcon: Boolean = true,
    firstIconClick: () -> Unit,
    secondIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showFirstIcon) {
            IconButton(onClick = { firstIconClick() }) {
                Icon(painter = firstIcon, contentDescription = "")
            }
        }

        IconButton(onClick = { secondIconClick() }) {
            Icon(painter = secondIcon, contentDescription = "")
        }

    }
}

@Composable
fun HeaderWithBackButtonAndLogo(onClick: () -> Unit) {
    HeaderWithOneIcon(
        icon = painterResource(id = R.drawable.ic_back_arrow)
    ) {
        onClick()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_itapevi),
            contentDescription = "",
            modifier = Modifier.width(200.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun HeaderWithImageAndIcon(imageResId: Int, iconTint: Color = PrimaryBlack, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = imageResId),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(modifier = Modifier.padding(32.dp), onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = iconTint
                )
            }
        }

    }
}

@Preview
@Composable
fun HeadersPreview() {
    Column {
        HeaderWithOneIcon(
            icon = painterResource(id = R.drawable.ic_back_arrow)
        ) {}

        HeaderWithTwoIcons(
            arrangement = Arrangement.End,
            firstIconClick = {},
            secondIconClick = {},
            showFirstIcon = false,
            secondIcon = painterResource(id = R.drawable.ic_profile)
        )
    }

}
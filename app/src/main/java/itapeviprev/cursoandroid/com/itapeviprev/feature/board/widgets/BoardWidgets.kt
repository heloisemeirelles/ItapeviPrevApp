package itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.TransparentWhite

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoardSlider() {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.board_first_banner),
        painterResource(id = R.drawable.board_second_banner),
    )

    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        HorizontalPager(
            state = pagerState,
            count = imageSlider.size,
            modifier = Modifier.height(148.dp), // Ajuste o padding horizontal conforme necessário
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
            itemSpacing = 8.dp// Padding para mostrar parte da próxima imagem

        ) { page ->
            Image(
                painter = imageSlider[page],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun BoardOptionCards() {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, PrimaryLightGray),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryLightGrayTransparent
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(modifier = Modifier
                .padding(top = 12.dp)
                .size(80.dp), painter = painterResource(id = R.drawable.ic_benefits), contentDescription = "")
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = stringResource(id = R.string.benefits),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                )
            Spacer(modifier = Modifier.size(16.dp))
            Icon(painter = painterResource(id = R.drawable.ic_arrow_forward), contentDescription = "")


        }
    }
}

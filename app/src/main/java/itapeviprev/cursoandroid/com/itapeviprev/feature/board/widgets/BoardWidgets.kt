package itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoardSlider(imageSlider: List<BoardSliderData>) {
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
            count = imageSlider.size,
            modifier = Modifier
                .height(148.dp),
            contentPadding = PaddingValues(end = 24.dp),
            itemSpacing = 8.dp

        ) { page ->
            BoardItem(imageSlider, page)
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
private fun BoardItem(
    imageSlider: List<BoardSliderData>,
    page: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(
                imageSlider[page].backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = imageSlider[page].bannerTextId),
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
                Button(modifier = Modifier.padding(top = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    onClick = { imageSlider[page].onClickAction() }) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(id = R.string.access),
                        color = imageSlider[page].backgroundColor,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        tint = imageSlider[page].backgroundColor,
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = imageSlider[page].imageResId),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun CardGrid(cardDataList: List<CardData>, imageSlider: List<BoardSliderData>) {
    LazyColumn {
        item {
            BoardSlider(imageSlider)
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = stringResource(id = R.string.actions_pane),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        items(cardDataList.chunked(2)) { rowItems ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { cardData ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                end = if (rowItems.first() == cardData) 8.dp else 0.dp,
                                start = if (rowItems.last() == cardData) 8.dp else 0.dp,
                                bottom = 16.dp
                            ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, PrimaryLightGray),
                        colors = CardDefaults.cardColors(containerColor = PrimaryLightGrayTransparent)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .size(80.dp),
                                    painter = painterResource(id = cardData.imageResId),
                                    contentDescription = "",
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    modifier = Modifier
                                        .padding(4.dp),
                                    text = stringResource(id = cardData.textResId),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    minLines = 2,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                IconButton(onClick = { cardData.onClickAction() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

data class CardData(
    val imageResId: Int,
    val textResId: Int,
    val onClickAction: () -> Unit
)

data class BoardSliderData(
    val bannerTextId: Int,
    val imageResId: Int,
    val backgroundColor: Color,
    val onClickAction: () -> Unit
)



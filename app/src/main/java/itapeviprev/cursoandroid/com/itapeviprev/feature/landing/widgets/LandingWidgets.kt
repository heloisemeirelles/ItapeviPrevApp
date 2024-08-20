package itapeviprev.cursoandroid.com.itapeviprev.feature.landing.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import itapeviprev.cursoandroid.com.itapeviprev.R

import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlueLight
import itapeviprev.cursoandroid.com.itapeviprev.theme.TransparentBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.TransparentWhite
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSliderCard() {
    val pagerState = rememberPagerState(initialPage = 0)
    val cardBackgroundColor = remember {
        mutableStateOf(PrimaryBlue)
    }

    val selectedColor = remember {
        mutableStateOf(Color.Black)
    }

    val unselectedColor = remember {
        mutableStateOf(Color.White)

    }
    val imageSlider = listOf(
        ImageSliderData(
            painterResource(id = R.drawable.ic_banner_mobile),
            stringResource(id = R.string.itapevi_prev_changed),
            PrimaryBlue,
            Color.White,
            TransparentWhite
        ),
        ImageSliderData(
            painterResource(id = R.drawable.ic_banner_calculator),
            stringResource(id = R.string.check_funds_health),
            PrimaryBlueLight,
            Color.Black,
            TransparentBlack
        ),
        ImageSliderData(
            painterResource(id = R.drawable.ic_banner_coin),
            stringResource(id = R.string.you_always_up_to_date),
            PrimaryBlue,
            Color.White,
            TransparentWhite
        ),
        ImageSliderData(
            painterResource(id = R.drawable.ic_banner_benefits),
            stringResource(id = R.string.keep_informed_about_benefits),
            PrimaryBlueLight,
            Color.Black,
            TransparentBlack
        ),
    )


    Column(
        modifier = Modifier.background(
            cardBackgroundColor.value,
            shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        HorizontalPager(
            count = imageSlider.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBackgroundColor.value)
        ) { page ->
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(180.dp),
                    painter = imageSlider[page].icon,
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(24.dp))

                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = imageSlider[page].text,
                            style = MaterialTheme.typography.titleSmall,
                            color = imageSlider[page].selectedColor,
                            textAlign = TextAlign.Center,
                            minLines = 3,
                            modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)

                        )
                    }
                }
            }
        }

        PageIndicator(imageSlider, pagerState, cardBackgroundColor, selectedColor, unselectedColor)
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PageIndicator(
    imageSlider: List<ImageSliderData>,
    pagerState: PagerState,
    cardBackgroundColor: MutableState<Color>,
    selectedColor: MutableState<Color>,
    unselectedColor: MutableState<Color>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        imageSlider.forEachIndexed { index, item ->
            val selectedPage = index == pagerState.currentPage
            if (selectedPage) {
                cardBackgroundColor.value = item.backgroundColor
                selectedColor.value = item.selectedColor
                unselectedColor.value = item.unselectedColor
            }
            Box(
                modifier = Modifier
                    .size(if (selectedPage) 18.dp else 12.dp)
                    .background(
                        if (selectedPage) selectedColor.value else unselectedColor.value,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.size(12.dp))

        }
    }
}

@Composable
fun ButtonsContainer(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onNoAccountClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        RoundedButton(
            backgroundColor = PrimaryBlue,
            labelColor = Color.White,
            label = stringResource(id = R.string.login)
        ) {
            onLoginClick()
        }

        Spacer(modifier = Modifier.size(16.dp))

        RoundedButton(
            backgroundColor = Color.White,
            labelColor = PrimaryBlue,
            label = stringResource(id = R.string.login_without_account),
            borderColor = PrimaryBlue
        ) { onNoAccountClick() }

        Spacer(modifier = Modifier.size(16.dp))

        RoundedButton(
            backgroundColor = Color.White,
            labelColor = PrimaryBlue,
            label = stringResource(id = R.string.create_my_account),

            ) { onCreateAccountClick() }
    }


}

@Preview
@Composable
fun LoginWidgetsPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Top) {
            Column(modifier = Modifier.fillMaxSize()) {
                BannerSliderCard()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    ButtonsContainer(onLoginClick = {},
                        onCreateAccountClick = {}) {}
                }
            }
        }

    }
}

data class ImageSliderData(
    val icon: Painter,
    val text: String,
    val backgroundColor: Color,
    val selectedColor: Color,
    val unselectedColor: Color,
)
package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.utils.ActionCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.utils.BenefitType
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.viewModel.BenefitViewModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue24
import itapeviprev.cursoandroid.com.itapeviprev.theme.TransparentBlack
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ButtonWithIcon


@Composable
fun BenefitScreen(
    navController: NavHostController,
    benefitType: String,
    viewModel: BenefitViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val phoneNumber = stringResource(id = R.string.phone_number)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BenefitHeader(imageResId = ActionCard(benefitType).getInfo().headerImageResId) { navController.popBackStack() }
            BenefitScreenBody(viewModel.showText, type = benefitType)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp)
            ) {
                ButtonWithIcon(
                    backgroundColor = Color.White,
                    textColor = PrimaryBlue,
                    buttonText = stringResource(id = viewModel.getHideShowButtonTextId()),
                    icon = viewModel.getHideShowButtonIcon(),
                    iconColor = PrimaryBlue
                ) {
                    viewModel.showText.value = !viewModel.showText.value
                }
                ContactCard { viewModel.dialPhoneNumber(context, phoneNumber) }
            }
        }
    }
}

@Composable
fun ContactCard(onClickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 32.dp)
            .clickable { onClickAction() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue24
        )
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone_contact),
                    contentDescription = "",
                )
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.telephone),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(id = R.string.formatted_phone_number),
                        color = PrimaryBlue,
                        style = MaterialTheme.typography.bodyLarge,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun BenefitHeader(imageResId: Int, onBackPressed: () -> Unit, ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(modifier = Modifier
                .padding(24.dp)
                .align(Alignment.CenterStart)
                .background(
                    TransparentBlack, shape = CircleShape
                ), onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
fun BenefitScreenBody(showEverything: MutableState<Boolean>, type: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
            text = stringResource(id = ActionCard(type).getInfo().screenTitleId),
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = getAnnotatedText(type = type),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = if (showEverything.value) Int.MAX_VALUE else 10,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun getAnnotatedText(type: String): AnnotatedString {
    val listOfTexts = ActionCard(type).getInfo().textBlock
    return buildAnnotatedString {
        listOfTexts.forEach { item ->
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(if(item.titleId == 0) "" else stringResource(item.titleId))
            }
            if(item.titleId != 0) {
                append("\n\n")
            }
            append(stringResource(item.textId))
            if(item != listOfTexts.last()) {
                append("\n\n")
            }
        }

    }
}

@Preview
@Composable
fun BenefitScreenPreview() {
    val navController = rememberNavController()
    BenefitScreen(navController, BenefitType.VolunteerRetirement.name)
}

data class BenefitTextBlock(
    val titleId: Int = 0,
    val textId: Int = 0
)
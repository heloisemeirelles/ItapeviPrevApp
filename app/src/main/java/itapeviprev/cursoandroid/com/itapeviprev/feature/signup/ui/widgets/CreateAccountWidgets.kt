package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.viewModel.CreateAccountViewModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue100

@Composable
fun IdentificationCard(cardInfo: CardInfo, isSelected: Boolean, selectedCard: (Int) -> Unit) {

    Card(Modifier
        .fillMaxWidth()
        .clickable {
            selectedCard(cardInfo.id)
        }
        .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 2.dp, color = if (isSelected) PrimaryBlue else PrimaryGray),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) PrimaryBlue100 else Color.White
        )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Transparent)
        ) {
            Icon(
                painter = painterResource(id = cardInfo.iconId),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = cardInfo.title, style = MaterialTheme.typography.bodyLarge)
        }
    }

}

@Composable
fun IdentificationCardsList(arrayOfCards: ArrayList<CardInfo>, selectedCard: MutableState<Int?>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (i in 0..1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(0.5f)
                ) {
                    IdentificationCard(arrayOfCards[i], arrayOfCards[i].id == selectedCard.value) {
                        selectedCard.value = it
                    }
                }
                if (i == 0) Spacer(modifier = Modifier.size(16.dp))
            }

        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                IdentificationCard(arrayOfCards[2], arrayOfCards[2].id == selectedCard.value) {
                    selectedCard.value = it
                }
            }
        }


    }

}

@Composable
fun listOfIdentification(): ArrayList<CardInfo> {
    return arrayListOf(
        CardInfo(
            id = CreateAccountViewModel.RETIREE,
            stringResource(id = R.string.retiree),
            R.drawable.ic_umbrella,
            isSelected = false
        ),
        CardInfo(
            id = CreateAccountViewModel.PENSIONER,
            stringResource(id = R.string.pensioner),
            R.drawable.ic_person,
            isSelected = false
        ),
        CardInfo(
            id = CreateAccountViewModel.OTHER,
            stringResource(id = R.string.others),
            R.drawable.ic_group,
            isSelected = false
        )
    )
}

data class CardInfo(
    val id: Int,
    val title: String,
    val iconId: Int,
    val isSelected: Boolean
)




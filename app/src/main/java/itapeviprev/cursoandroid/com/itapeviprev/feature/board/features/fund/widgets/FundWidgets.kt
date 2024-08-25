package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model.FundInfoCardModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.OrangeLight
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue24
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent

@Composable
fun FundInfoCard(fundCardInfo: FundInfoCardModel) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = PrimaryLightGrayTransparent
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(shape = CircleShape, color = PrimaryLightGray)
                        .padding(8.dp),
                    painter = painterResource(id = fundCardInfo.iconId),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = fundCardInfo.titleId),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            fundCardInfo.listOfTextBlocks.forEach { item ->
                TextBlock(
                    item.titleId,
                    item.textId
                )
                if (item == fundCardInfo.listOfTextBlocks.last()) {
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

        }
    }
}

@Composable
fun TextBlock(titleId: Int, textId: Int) {
    Text(
        modifier = Modifier.padding(top = 16.dp), text = stringResource(id = titleId),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = stringResource(id = textId),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun OpeningHoursCard() {
    Card(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrangeLight
        ),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "", tint = PrimaryBlack)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.opening_hours),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.monday_to_friday),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun ContactCard(contact: ContactModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue24)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = contact.iconId), contentDescription = "")
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(0.9f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = contact.titleId),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = contact.textId),
                    color = PrimaryBlue,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            IconButton(onClick = { contact.onClick() }) {
                Icon(
                    modifier = Modifier.size((34.dp)),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
        }
    }
}

data class ContactModel(
    val iconId: Int,
    val titleId: Int,
    val textId: Int,
    val onClick: () -> Unit
)




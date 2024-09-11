package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent24

@Composable
fun CandidatesFilterCard(option: CandidatesFilterOption, isSelected: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            },
        shape = CircleShape,
        colors = CardDefaults.cardColors(if (isSelected) Color.Black else Color.White),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = stringResource(id = option.titleId),
            color = if (isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp, 8.dp)
        )
    }
}

@Composable
fun CandidateCard(candidate: CandidateModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryLightGrayTransparent24)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = candidate.imgResId),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(id = candidate.name),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = stringResource(id = candidate.nickName),
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }

    }
}

data class CandidatesFilterOption(
    val id: Int,
    val titleId: Int,
)

data class CandidateModel(
    val type: Int,
    val name: Int,
    val nickName: Int,
    val imgResId: Int
)

object CandidatesFilterTypes {
    const val ALL_CANDIDATES = 1
    const val ADMINISTRATIVE_CANDIDATES = 2
    const val FISCAL_CANDIDATES = 3
}
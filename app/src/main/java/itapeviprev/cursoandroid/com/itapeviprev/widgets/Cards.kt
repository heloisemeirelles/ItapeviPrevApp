package itapeviprev.cursoandroid.com.itapeviprev.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray

@Composable
fun GenericActionCard(titleId: Int, imageResId: Int, onClickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, color = PrimaryLightGray),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = imageResId),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                fontWeight = FontWeight.Normal
            )
            ButtonWithIcon(backgroundColor = PrimaryBlue, textColor = Color.White) {onClickAction()}

        }
    }
}
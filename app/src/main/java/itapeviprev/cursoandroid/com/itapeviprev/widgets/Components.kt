package itapeviprev.cursoandroid.com.itapeviprev.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.InfoModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.Green
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import kotlinx.coroutines.delay

@Composable
fun InfoRow(profileInfoModel: InfoModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(24.dp),
            imageVector = profileInfoModel.imageVector,
            contentDescription = "",
            tint = PrimaryBlack
        )
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = profileInfoModel.titleId),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp),
                text = profileInfoModel.text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Divider(color = PrimaryLightGray)
}

@Composable
fun CustomCircularProgressBar() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            color = PrimaryBlue,
            strokeCap = StrokeCap.Round,
            strokeWidth = 5.dp
        )
    }

}

@Composable
fun CustomToast(
    title: String,
    message: String,
    showToast: Boolean,
    onDismiss: () -> Unit
) {
    if (showToast) {
        // Display the toast
        LaunchedEffect(Unit) {
            delay(2000) // Toast duration
            onDismiss() // Dismiss after delay
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Green, shape = RoundedCornerShape(50.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Green, shape = CircleShape)
                    .clip(
                        CircleShape
                    )
                    .padding(8.dp)) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "")
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                    verticalArrangement = Arrangement.Center) {
                    Text(
                        text = title,
                        color = PrimaryBlack,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = message,
                        color = PrimaryBlack,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }


        }
    }
}
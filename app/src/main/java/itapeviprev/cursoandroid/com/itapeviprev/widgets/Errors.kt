package itapeviprev.cursoandroid.com.itapeviprev.widgets

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue

@Composable
fun ErrorCard(onDismissClick: () -> kotlin.Unit,
              onTryAgain: () -> Unit,
              onCloseApp: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Icon(modifier = Modifier.clickable {
                    onDismissClick()
                }, imageVector = Icons.Outlined.Close, contentDescription = "")
            }

            Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
            Text(text = stringResource(id = R.string.something_went_wrong),
                modifier = Modifier.padding(top = 24.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.size(24.dp))
            RoundedButton(backgroundColor = PrimaryBlue, labelColor = Color.White, label = stringResource(
                id = R.string.try_again
            )) {
                onTryAgain()
            }
            Spacer(modifier = Modifier.size(24.dp))

            RoundedButton(backgroundColor = Color.White, labelColor = PrimaryBlue, borderColor = PrimaryBlue, label = stringResource(
                id = R.string.close_app
            )) {
                onCloseApp()
            }
        }
    }
}

@Composable
fun ErrorDialog(onDismissClick: () -> Unit,
                onTryAgain: () -> Unit,
                onCloseApp: () -> Unit,
                ){
    Dialog(onDismissRequest = { onDismissClick() }) {
        ErrorCard(onDismissClick = {onDismissClick()}, onTryAgain = { onTryAgain() }) {
            onCloseApp()
        }
    }

}
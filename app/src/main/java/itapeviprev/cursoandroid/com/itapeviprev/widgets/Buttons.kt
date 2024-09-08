package itapeviprev.cursoandroid.com.itapeviprev.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray

@Composable
fun RoundedButton(
    backgroundColor: Color,
    labelColor: Color,
    label: String,
    borderColor: Color = Color.Transparent,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    fontWeight: FontWeight = FontWeight.SemiBold,
    onClick: () -> Unit,

    ) {
    Button(
        enabled = enabled,
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = labelColor,
            disabledContainerColor = PrimaryLightGray,
            disabledContentColor = PrimaryGray
        ),
        border = BorderStroke(2.dp, color = borderColor)
    ) {
        if (isLoading) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(id = R.string.loading),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = fontWeight,
                    color = labelColor,
                    textAlign = TextAlign.Center
                )
            }

        } else {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = fontWeight,
                color = labelColor,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center

            )
        }
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        RoundedButton(
            backgroundColor = PrimaryBlue,
            labelColor = Color.White,
            label = stringResource(id = R.string.login)
        ) {}

        Spacer(modifier = Modifier.size(16.dp))
        RoundedButton(
            backgroundColor = Color.White,
            labelColor = PrimaryBlue,
            label = stringResource(id = R.string.login_without_account),
            borderColor = PrimaryBlue
        ) {}

        RoundedButton(
            backgroundColor = Color.White,
            labelColor = PrimaryBlue,
            label = stringResource(id = R.string.login_without_account),
            borderColor = PrimaryBlue,
            isLoading = true
        ) {}
    }

}

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryBlue,
    textColor: Color = Color.White,
    iconColor: Color = Color.White,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    buttonText: String = stringResource(id = R.string.access),
    borderColor: Color = PrimaryBlue,
    onClick: () -> Unit
) {
    Button(modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        contentPadding = PaddingValues(14.dp),
        border = BorderStroke(2.dp, color = borderColor),
        onClick = { onClick() }) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = buttonText,
            color = textColor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Icon(
            tint = iconColor,
            imageVector = icon,
            contentDescription = ""
        )
    }
}
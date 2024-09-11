package itapeviprev.cursoandroid.com.itapeviprev.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryRed

@Composable
fun RoundedTextField(
    text: MutableState<String>,
    placeholder: String,
    leadingIconId: Int = 0,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIconId: Int = 0,
    iconTint: Color = Color.Black,
    borderColor: Color = PrimaryGray,
    showErrorMessage: Boolean = false,
    backgroundColor: Color = PrimaryLightGrayTransparent,
    singleLine: Boolean = true,
    errorMessage: String = "",
    isOnFocus: MutableState<Boolean> = mutableStateOf(false),
    maxLength: Int = 0
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White)
            .onFocusChanged {
                isOnFocus.value = it.isFocused
            }
    ) {
        TextField(
            modifier = Modifier
                .border(
                    width = if (text.value.isNotEmpty() || isOnFocus.value) 2.dp else 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = if (isOnFocus.value && !showErrorMessage) PrimaryBlue else borderColor
                )
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIconId), contentDescription = "",
                    tint = iconTint
                )
            },
            trailingIcon = {
                if (trailingIconId != 0) {
                    Icon(
                        painter = painterResource(id = trailingIconId), contentDescription = "",
                        tint = iconTint
                    )
                }
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },

            value = text.value, onValueChange = { newText ->
                if (newText.all { it.isDigit() || it == '.' || it == ',' }) {
                    text.value = if (maxLength != 0) {
                        newText.take(maxLength)
                    } else {
                        newText
                    }
                }

            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            singleLine = singleLine
        )

        if (showErrorMessage) {
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error_alert),
                    contentDescription = "",
                    tint = PrimaryRed
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = errorMessage, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun PasswordTextField(
    borderColor: Color = PrimaryGray,
    iconTint: Color = Color.Black,
    placeholder: String = "",
    text: MutableState<String>,
    backgroundColor: Color = PrimaryLightGray,
    showPassword: MutableState<Boolean>,
    showErrorMessage: Boolean = false,
    errorMessage: String = "",
    isOnFocus: MutableState<Boolean> = mutableStateOf(false)
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White)
            .onFocusChanged {
                isOnFocus.value = it.isFocused
            }
    ) {
        TextField(
            modifier = Modifier
                .border(
                    width = if (text.value.isNotEmpty() || isOnFocus.value) 2.dp else 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = if (isOnFocus.value && !showErrorMessage) PrimaryBlue else borderColor
                )
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock), contentDescription = "",
                    tint = iconTint
                )
            },
            trailingIcon = {
                val trailingIconId =
                    if (showPassword.value) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off
                IconButton(
                    onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        painter = painterResource(id = trailingIconId), contentDescription = "",
                        tint = iconTint,
                    )
                }


            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = PrimaryGray
                )
            },

            value = text.value, onValueChange = { text.value = it },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
        )

        if (showErrorMessage) {
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error_alert),
                    contentDescription = "",
                    tint = PrimaryRed
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = errorMessage, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
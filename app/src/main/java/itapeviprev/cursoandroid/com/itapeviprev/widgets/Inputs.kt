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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
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
    maxLength: Int = 0,
    isDecimal: Boolean = false,
    format: (newText: String) -> String = { ("") }
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
                if (isDecimal) {
                    if (newText.all { it.isDigit() || it == '.' || it == ',' }) {
                        text.value = getNewText(maxLength, newText, format)
                    }
                } else {
                    text.value = getNewText(maxLength, newText, format)
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

private fun getNewText(
    maxLength: Int,
    newText: String,
    format: (data: String) -> String,
): String {
    val newValue = if (maxLength != 0) {
        newText.take(maxLength)
    } else {
        newText
    }

    return if (format(newValue) != "") {
        format(newValue)
    } else {
        newValue
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

fun formatToMask(text: String): String {
    return when {
        text.length <= 3 -> text
        text.length <= 6 -> "${text.substring(0, 3)}.${text.substring(3)}"
        text.length <= 9 -> "${text.substring(0, 3)}.${text.substring(3, 6)}.${text.substring(6)}"
        else -> "${text.substring(0, 3)}.${text.substring(3, 6)}.${
            text.substring(
                6,
                9
            )
        }-${text.substring(9)}"
    }
}

class MaskedVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val cleanedText = originalText.filter { it.isDigit() }
        val formattedText = formatToMask(cleanedText)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val cleaned = originalText.take(offset).filter { it.isDigit() }
                val formatted = formatToMask(cleaned)
                return formatted.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                val cleaned = formattedText.take(offset).filter { it.isDigit() }
                return cleaned.length
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping
        )
    }
}


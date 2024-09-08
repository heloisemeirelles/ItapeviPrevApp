package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.FieldTitle
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedTextField
import itapeviprev.cursoandroid.com.itapeviprev.widgets.formatDateInput

@Composable
fun ProfileInfoRow(profileInfoModel: ProfileInfoModel) {
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
                modifier = Modifier.padding(top = 4.dp), text = profileInfoModel.text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Divider(color = PrimaryLightGray)
}

@Composable
fun UpdateDataDialog(
    closeDialog: () -> Unit,
    updateUserInfo: () -> Unit
) {
    Dialog(onDismissRequest = { closeDialog() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { closeDialog() }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "",
                            tint = PrimaryBlack
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Outlined.Create,
                    contentDescription = "",
                    tint = PrimaryBlack
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                    text = stringResource(id = R.string.update_your_personal_data),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                RoundedButton(
                    backgroundColor = PrimaryBlue, labelColor = Color.White, label = stringResource(
                        id = R.string.update_now
                    )
                ) {
                    updateUserInfo()
                }
                Spacer(modifier = Modifier.size(24.dp))
                RoundedButton(
                    backgroundColor = Color.White, labelColor = PrimaryBlue, label = stringResource(
                        id = R.string.update_later
                    ), borderColor = PrimaryBlue
                ) {
                    closeDialog()
                }
            }
        }
    }
}

@Composable
fun DateOfBirthField(
    dateOfBirth: MutableState<String>,
    dateOfBirthHasFocus: MutableState<Boolean>,
    borderColor: Color,
    backgroundColor: Color,
    showErrorMessage: Boolean
) {
    FieldTitle(title = stringResource(id = R.string.date_of_birth))
    RoundedTextField(text = dateOfBirth,
        placeholder = stringResource(id = R.string.date_of_birth_placeholder),
        leadingIconId = R.drawable.ic_calendar,
        isOnFocus = dateOfBirthHasFocus,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLength = 8,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        visualTransformation = {
            formatDateInput(AnnotatedString(dateOfBirth.value))
        },
        showErrorMessage = showErrorMessage,
        errorMessage = stringResource(id = R.string.invalid_date)
    )
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun FullNameField(
    fullName: MutableState<String>,
    fullNameHasFocus: MutableState<Boolean>,
    showErrorMessage: Boolean,
    backgroundColor: Color,
    borderColor: Color
) {
    FieldTitle(title = stringResource(id = R.string.full_name))
    RoundedTextField(
        text = fullName,
        placeholder = stringResource(id = R.string.enter_your_full_name),
        leadingIconId = R.drawable.ic_profile,
        isOnFocus = fullNameHasFocus,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        showErrorMessage = showErrorMessage,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        errorMessage = stringResource(id = R.string.fullname_invalid)
    )
    Spacer(modifier = Modifier.size(16.dp))
}

data class ProfileInfoModel(
    val imageVector: ImageVector,
    val titleId: Int,
    val text: String
)
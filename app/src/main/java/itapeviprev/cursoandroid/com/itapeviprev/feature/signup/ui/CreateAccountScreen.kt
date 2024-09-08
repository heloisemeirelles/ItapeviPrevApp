package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.widgets.IdentificationCardsList
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.widgets.listOfIdentification
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.viewModel.CreateAccountViewModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ErrorDialog
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithBackButtonAndLogo
import itapeviprev.cursoandroid.com.itapeviprev.widgets.PasswordTextField
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedTextField
import itapeviprev.cursoandroid.com.itapeviprev.widgets.formatDateInput
import kotlin.system.exitProcess

@Composable
fun CreateAccountScreen(
    navController: NavController,
    viewModel: CreateAccountViewModel = hiltViewModel()
) {
    val fullNameHasFocus = remember { mutableStateOf(false) }
    val emailHasFocus = remember { mutableStateOf(false) }
    val isAddingUser = remember { mutableStateOf(false) }
    val dateOfBirthHasFocus = remember { mutableStateOf(false) }
    val passwordHasFocus = remember { mutableStateOf(false) }
    val showPassword = remember { mutableStateOf(false) }
    val confirmPasswordHasFocus = remember { mutableStateOf(false) }
    val showConfirmPassword = remember { mutableStateOf(false) }
    val showErrorDialog = remember { mutableStateOf(false) }

    val signUpState by viewModel.signUpState.collectAsState()

    when (signUpState) {
        is SignUpState.Completed -> {
            navController.navigate(BoardNavigationScreens.BoardScreen.name)
        }

        is SignUpState.Loading -> {
            isAddingUser.value = true
        }

        is SignUpState.Error -> {
            showErrorDialog.value = true
        }

        else -> {
            isAddingUser.value = false
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderWithBackButtonAndLogo { navController.popBackStack() }
            Spacer(modifier = Modifier.size(32.dp))

            FullNameField(
                viewModel.fullName, fullNameHasFocus, viewModel.showFullNameError(),
                viewModel.fieldBackgroundColor(viewModel.showFullNameError()),
                viewModel.fieldBorderColor(viewModel.showFullNameError())
            )

            DateOfBirthField(viewModel.dateOfBirth, dateOfBirthHasFocus)
            EmailTextField(
                viewModel.email, viewModel.fieldBackgroundColor(viewModel.showEmailError()),
                viewModel.fieldBorderColor(viewModel.showEmailError()),
                viewModel.showEmailError(), emailHasFocus
            )
            PasswordField(
                text = viewModel.password,
                backgroundColor = viewModel.fieldBackgroundColor(viewModel.passwordHasError.value),
                borderColor = viewModel.fieldBorderColor(viewModel.passwordHasError.value),
                isOnFocus = passwordHasFocus,
                showPassword = showPassword,
                showErrorMessage = viewModel.showPasswordError()
            )
            ConfirmPasswordField(
                viewModel.confirmPassword,
                viewModel.fieldBackgroundColor(viewModel.showConfirmPasswordError()),
                viewModel.fieldBorderColor(viewModel.showConfirmPasswordError()),
                isOnFocus = confirmPasswordHasFocus,
                showPassword = showConfirmPassword,
                showErrorMessage = viewModel.showConfirmPasswordError()
            )

            FieldTitle(title = stringResource(id = R.string.identify_yourself_below))
            Spacer(modifier = Modifier.size(8.dp))
            IdentificationCardsList(listOfIdentification(), viewModel.selectedIdentificationCard)
            Spacer(modifier = Modifier.size(32.dp))
            RoundedButton(
                backgroundColor = PrimaryBlue, labelColor = Color.White, label = stringResource(
                    id = R.string.create_my_account,
                ),
                isLoading = viewModel.isAddingUser.value,
                enabled = viewModel.isButtonEnabled()
            ) {
                viewModel.createAccount()
            }

            if(showErrorDialog.value) {
                ErrorDialog(onDismissClick = { showErrorDialog.value = false },
                    onTryAgain = {
                        showErrorDialog.value = false
                        viewModel.createAccount()
                    }) {
                    exitProcess(0)
                }
            }
        }
    }
}

@Composable
fun PasswordField(
    text: MutableState<String>,
    backgroundColor: Color,
    borderColor: Color,
    isOnFocus: MutableState<Boolean>,
    showPassword: MutableState<Boolean>,
    showErrorMessage: Boolean
) {
    FieldTitle(title = stringResource(id = R.string.password))
    PasswordTextField(
        text = text,
        placeholder = stringResource(id = R.string.type_your_password),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        errorMessage = stringResource(id = R.string.min_characters_password),
        showErrorMessage = showErrorMessage,
        showPassword = showPassword,
        isOnFocus = isOnFocus
    )
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun ConfirmPasswordField(
    text: MutableState<String>,
    backgroundColor: Color,
    borderColor: Color,
    isOnFocus: MutableState<Boolean>,
    showPassword: MutableState<Boolean>,
    showErrorMessage: Boolean,
) {
    FieldTitle(title = stringResource(id = R.string.confirm_password))
    PasswordTextField(
        text = text,
        placeholder = stringResource(id = R.string.confirm_your_password),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        showErrorMessage = showErrorMessage,
        showPassword = showPassword,
        isOnFocus = isOnFocus,
        errorMessage = stringResource(id = R.string.passwords_do_not_match),

        )
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun DateOfBirthField(
    dateOfBirth: MutableState<String>,
    dateOfBirthHasFocus: MutableState<Boolean>
) {
    FieldTitle(title = stringResource(id = R.string.date_of_birth))
    RoundedTextField(text = dateOfBirth,
        placeholder = stringResource(id = R.string.date_of_birth_placeholder),
        leadingIconId = R.drawable.ic_calendar,
        isOnFocus = dateOfBirthHasFocus,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLength = 8,
        visualTransformation = {
            formatDateInput(AnnotatedString(dateOfBirth.value))
        }
    )
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun FullNameField(
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

@Composable
private fun EmailTextField(
    email: MutableState<String>,
    backgroundColor: Color,
    borderColor: Color,
    emailHasError: Boolean,
    emailHasFocus: MutableState<Boolean>
) {
    Text(
        text = "${stringResource(id = R.string.email)}*",
        style = MaterialTheme.typography.bodyMedium
    )
    Spacer(modifier = Modifier.size(8.dp))

    RoundedTextField(
        text = email,
        placeholder = stringResource(id = R.string.type_your_email),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIconId = R.drawable.ic_email,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        showErrorMessage = emailHasError,
        errorMessage = stringResource(id = R.string.invalid_email),
        isOnFocus = emailHasFocus
    )

    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun FieldTitle(title: String) {
    Text(
        text = "${title}*",
        style = MaterialTheme.typography.bodyMedium
    )
    Spacer(modifier = Modifier.size(8.dp))
}




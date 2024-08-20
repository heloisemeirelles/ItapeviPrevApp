package itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.viewModel.LoginViewModel
import itapeviprev.cursoandroid.com.itapeviprev.navigation.AppNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithOneIcon
import itapeviprev.cursoandroid.com.itapeviprev.widgets.PasswordTextField
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedTextField

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val showPassword = remember { mutableStateOf(false) }
    val loginState by viewModel.loginState.collectAsState()
    val isLoading = remember { mutableStateOf(false) }

    when (loginState) {
        is LoginState.Loading -> {
            isLoading.value = true
        }

        is LoginState.Completed -> {
            isLoading.value = false
            navController.navigate(BoardNavigationScreens.BoardScreen.name)
        }

        is LoginState.Error -> {
            isLoading.value = false
        }

        is LoginState.InvalidEmail -> {
            isLoading.value = false
        }

        is LoginState.InvalidPassword -> {
            isLoading.value = false
        }

        else -> {
            isLoading.value = false
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color.White)

        ) {
            Column(
                Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                HeaderWithOneIcon(
                    icon = painterResource(id = R.drawable.ic_back_arrow)
                ) {
                    navController.popBackStack()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_itapevi),
                        contentDescription = "",
                        modifier = Modifier.width(200.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    EmailTextField(
                        email = viewModel.email,
                        backgroundColor = viewModel.fieldBackgroundColor(viewModel.emailHasError()),
                        borderColor = viewModel.fieldBorderColor(
                            viewModel.emailHasError(),
                            viewModel.isEmailFocused.value
                        ),
                        hasError = viewModel.emailHasError(),
                        isOnFocus = viewModel.isEmailFocused

                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    PasswordField(
                        password = viewModel.password,
                        backgroundColor = viewModel.fieldBackgroundColor(viewModel.passwordHasError()),
                        borderColor = viewModel.fieldBorderColor(
                            viewModel.passwordHasError(),
                            viewModel.isPasswordFocused.value
                        ),
                        hasError = viewModel.passwordHasError(),
                        showPassword = showPassword,
                        isOnFocus = viewModel.isPasswordFocused
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    RememberMeToggle(viewModel.rememberMe)
                    Spacer(modifier = Modifier.size(24.dp))
                    RoundedButton(
                        backgroundColor = PrimaryBlue,
                        labelColor = Color.White,
                        label = stringResource(
                            id = R.string.signin
                        ),
                        isLoading = isLoading.value,
                        enabled = viewModel.isLoginButtonEnabled()
                    ) {
                        if (!isLoading.value) {
                            viewModel.login()
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun RememberMeToggle(rememberMe: MutableState<Boolean>) {

    Row(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.remember_me),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(16.dp))
        Switch(
            checked = rememberMe.value, onCheckedChange = { rememberMe.value = !rememberMe.value },
            colors = androidx.compose.material3.SwitchDefaults.colors(
                checkedTrackColor = Color.Blue,
                checkedThumbColor = Color.White,
                uncheckedBorderColor = Color.Blue,
                uncheckedTrackColor = Color.White,
                uncheckedThumbColor = Color.Blue,
            )
        )
    }
}

@Composable
private fun PasswordField(
    password: MutableState<String>,
    backgroundColor: Color,
    borderColor: Color,
    hasError: Boolean,
    showPassword: MutableState<Boolean>,
    isOnFocus: MutableState<Boolean>
) {
    Text(
        text = stringResource(id = R.string.password),
        style = MaterialTheme.typography.bodyMedium
    )
    Spacer(modifier = Modifier.size(8.dp))
    PasswordTextField(
        text = password,
        placeholder = stringResource(id = R.string.type_your_password),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        showErrorMessage = hasError,
        showPassword = showPassword,
        errorMessage = stringResource(id = R.string.invalid_password),
        isOnFocus = isOnFocus
    )
}

@Composable
private fun EmailTextField(
    email: MutableState<String>,
    backgroundColor: Color,
    borderColor: Color,
    hasError: Boolean,
    isOnFocus: MutableState<Boolean>
) {
    Text(
        text = stringResource(id = R.string.email),
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
        showErrorMessage = hasError,
        errorMessage = stringResource(id = R.string.invalid_email),
        isOnFocus = isOnFocus
    )
}
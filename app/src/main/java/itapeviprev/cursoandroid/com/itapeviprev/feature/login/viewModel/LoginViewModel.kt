package itapeviprev.cursoandroid.com.itapeviprev.feature.login.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui.LoginState
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightRed
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryRed
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    val email = mutableStateOf("")
    private val emailIsInvalid = mutableStateOf(false)
    private val passwordIsInvalid = mutableStateOf(false)
    val password = mutableStateOf("")
    val rememberMe = mutableStateOf(false)
    val isEmailFocused = mutableStateOf(false)
    val isPasswordFocused = mutableStateOf(false)
    private val originalEmail = mutableStateOf("")
    private val originalPassword = mutableStateOf("")
    private var firebaseAuth: FirebaseAuth = Firebase.auth

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    fun fieldBorderColor(hasError: Boolean, isFocused: Boolean): Color {
        return if(hasError) {
            PrimaryRed
        } else {
            PrimaryGray
        }
    }

    fun fieldBackgroundColor(hasError: Boolean): Color {
        if(!hasError) {
            return PrimaryLightGrayTransparent
        } else {
            return PrimaryLightRed
        }
    }

    fun login() {
        resetErrors()
        _loginState.value = LoginState.Loading
        firebaseAuth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener {task ->
                if(task.isSuccessful) {
                    if(rememberMe.value) saveUserCredential()
                    _loginState.value = LoginState.Completed
                } else {
                    setValidationError(task.exception)
                    _loginState.value = LoginState.Error(task.exception?.message.toString())
                }
            }
    }

    fun isLoginButtonEnabled(): Boolean {
        return email.value.isNotEmpty() && password.value.isNotEmpty()
    }

    private fun saveUserCredential() {
        viewModelScope.launch {
            dataStoreManager.saveUserCredential(email.value)
        }
    }

    private fun resetErrors() {
        emailIsInvalid.value = false
        passwordIsInvalid.value = false
        originalEmail.value = email.value
        originalPassword.value = password.value
    }

    fun emailHasError(): Boolean {
        return originalEmail.value == email.value && emailIsInvalid.value
    }

    fun passwordHasError(): Boolean {
        return originalPassword.value == password.value && passwordIsInvalid.value
    }


    private fun setValidationError(exception: Exception?) {
        when(exception) {
            is FirebaseAuthInvalidCredentialsException -> {
                passwordIsInvalid.value = true
            }
            is FirebaseAuthInvalidUserException -> {
                emailIsInvalid.value = originalEmail.value == email.value
            }
        }
    }
}
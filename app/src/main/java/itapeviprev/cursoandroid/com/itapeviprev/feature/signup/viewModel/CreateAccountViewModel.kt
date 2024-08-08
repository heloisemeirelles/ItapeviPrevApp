package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.common.Errors
import itapeviprev.cursoandroid.com.itapeviprev.common.FirebaseStatus
import itapeviprev.cursoandroid.com.itapeviprev.common.Resource
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui.LoginState
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.domain.AddUserUseCase
import itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui.SignUpState
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightRed
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryRed
import itapeviprev.cursoandroid.com.itapeviprev.widgets.emailIsValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val fullName = mutableStateOf("")
    val dateOfBirth = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")
    val selectedIdentificationCard = mutableStateOf<Int?>(null)

    private val originalEmail = mutableStateOf("")
    private val originalPassword = mutableStateOf("")

    private val emailHasError = mutableStateOf(false)
    private val confirmPasswordHasError = mutableStateOf(false)
    val passwordHasError = mutableStateOf(false)

    val isAddingUser = mutableStateOf(false)

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Initial)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun fieldBorderColor(hasError: Boolean): Color {
        return if (hasError) PrimaryRed else PrimaryGray
    }

    fun fieldBackgroundColor(hasError: Boolean): Color {
        return if (!hasError) {
            PrimaryLightGrayTransparent
        } else {
            PrimaryLightRed
        }
    }

    private fun isFullNameValid(): Boolean {
        val numberOfWords = fullName.value.split("\\s+".toRegex())
        return numberOfWords.size > 1 && fullName.value.isNotEmpty() && numberOfWords[numberOfWords.lastIndex].trim()
            .isNotEmpty()
    }

    private fun isDateOfBirthValid(): Boolean {
        return dateOfBirth.value.length == 8
    }

    private fun isEmailValid(): Boolean {
        return !emailHasError.value && emailIsValid(email.value)
    }

    fun showEmailError(): Boolean {
        if (emailHasError.value && originalEmail.value != email.value) {
            emailHasError.value = false
        }
        return !isEmailValid() && email.value.isNotEmpty()
    }

    fun showFullNameError(): Boolean {
        return !isFullNameValid() && fullName.value.isNotEmpty()
    }

    fun showConfirmPasswordError(): Boolean {
        return !isConfirmPasswordValid() && confirmPassword.value.isNotEmpty()
    }

    private fun isPasswordValid(): Boolean {
        return !passwordHasError.value && password.value.isNotEmpty()
    }

    private fun isConfirmPasswordValid(): Boolean {
        return !confirmPasswordHasError.value && confirmPassword.value == password.value
    }

    private fun isIdentificationCardSelected(): Boolean {
        return selectedIdentificationCard.value != null
    }

    fun isButtonEnabled(): Boolean {
        return isFullNameValid() &&
                isDateOfBirthValid() &&
                isEmailValid() &&
                isPasswordValid() &&
                isConfirmPasswordValid() &&
                isIdentificationCardSelected()
    }

    private fun addUser() {
        val userEntity = UserEntity(
            fullName = fullName.value,
            dateOfBirth = dateOfBirth.value,
            email = email.value,
            identification = selectedIdentificationCard.value
        )
        viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase.invoke(userEntity, password.value).collect() { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _signUpState.value = SignUpState.Loading
                    }

                    is Resource.Success -> {
                        saveUserCredential()
                        _signUpState.value = SignUpState.Completed
                    }

                    is Resource.Error -> {
                        _signUpState.value = SignUpState.Error(Errors.valueOf(resource.message))
                    }
                }
            }
        }
    }

    private fun saveUserCredential() {
        viewModelScope.launch {
            dataStoreManager.saveUserCredential(email.value)
        }
    }

    fun showPasswordError(): Boolean {
        if (passwordHasError.value && originalPassword.value != password.value) {
            passwordHasError.value = false
        }
        return passwordHasError.value && password.value.isNotEmpty()
    }

    private fun resetErrors() {
        passwordHasError.value = false
        emailHasError.value = false
    }

    fun createAccount() {
        originalPassword.value = password.value
        originalEmail.value = email.value
        resetErrors()
        _signUpState.value = SignUpState.Loading
        isAddingUser.value = true
        val firebaseAuth: FirebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    addUser()
                } else {
                    isAddingUser.value = false
                    with(task.exception.toString()) {
                        when (true) {
                            this.contains(FirebaseStatus.FirebaseAuthWeakPasswordException.name) -> {
                                passwordHasError.value = true
                                _signUpState.value = SignUpState.Initial
                            }

                            this.contains(FirebaseStatus.FirebaseAuthUserCollisionException.name) -> {
                                emailHasError.value = true
                                _signUpState.value = SignUpState.Initial
                            }

                            else -> {
                                _signUpState.value = SignUpState.Error(Errors.UnexpectedError)
                            }
                        }
                    }

                }
            }
    }

    companion object {
        const val RETIREE = 0
        const val PENSIONER = 1
        const val OTHER = 2
    }
}



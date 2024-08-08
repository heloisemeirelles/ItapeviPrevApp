package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui

import itapeviprev.cursoandroid.com.itapeviprev.common.Errors

sealed class SignUpState {
    data object Initial : SignUpState()
    data object Loading : SignUpState()
    data object Completed : SignUpState()
    data object InvalidEmail : SignUpState()
    data object InvalidPassword : SignUpState()
    data object PasswordIsTooWeak : SignUpState()
    data object EmailAlreadyExists : SignUpState()
    data class Error(val errors: Errors) : SignUpState()
}
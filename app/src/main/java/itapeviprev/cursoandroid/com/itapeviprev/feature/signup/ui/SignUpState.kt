package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.ui

import itapeviprev.cursoandroid.com.itapeviprev.common.Errors

sealed class SignUpState {
    data object Initial : SignUpState()
    data object Loading : SignUpState()
    data object Completed : SignUpState()
    data class Error(val errors: Errors) : SignUpState()
}
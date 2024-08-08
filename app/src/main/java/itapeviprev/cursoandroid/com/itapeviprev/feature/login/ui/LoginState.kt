package itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui

sealed class LoginState {
    data object Initial: LoginState()
    data object Loading: LoginState()
    data object Completed: LoginState()
    data object InvalidEmail: LoginState()
    data object InvalidPassword: LoginState()
    data object PasswordIsTooWeak: LoginState()
    data object EmailAlreadyExists: LoginState()
    data class Error(val error: String): LoginState()
}
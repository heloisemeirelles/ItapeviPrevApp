package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile

import itapeviprev.cursoandroid.com.itapeviprev.common.Errors

sealed class GetUserState {
    data object Initial : GetUserState()
    data object Loading : GetUserState()
    data object Complete : GetUserState()
    data object UserSignedOut : GetUserState()
    data object ErrorSignOut : GetUserState()
    data object UserEntityIsEmpty : GetUserState()
    data object GenericError : GetUserState()
    data object FailedGetUser : GetUserState()
    data object FailedUpdateUser : GetUserState()
    data object InfoUpdated : GetUserState()
    data class Error(val errors: Errors) : GetUserState()
}
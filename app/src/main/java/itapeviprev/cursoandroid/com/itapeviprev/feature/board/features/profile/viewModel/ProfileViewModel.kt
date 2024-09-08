package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.GetUserState
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.ProfileInfoModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryGray
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGrayTransparent
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightRed
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryRed
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ErrorDialog
import itapeviprev.cursoandroid.com.itapeviprev.widgets.formatDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val itapeviPrevRepository: ItapeviPrevRepository
) :
    ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _getUserState = MutableStateFlow<GetUserState>(GetUserState.Initial)
    val getUserState: StateFlow<GetUserState> = _getUserState

    private val _profileInfoList = MutableStateFlow<List<ProfileInfoModel>>(arrayListOf())
    val profileInfoList: StateFlow<List<ProfileInfoModel>> = _profileInfoList

    val editView = mutableStateOf(false)
    val showDialog = mutableStateOf(false)

    val dateOfBirth = mutableStateOf("")
    val dateOfBirthHasFocus = mutableStateOf(false)
    val fullName = mutableStateOf("")
    val fullNameHasFocus = mutableStateOf(false)
    val isFullNameValid = mutableStateOf(false)

    val infoSuccessfullyUpdated = mutableStateOf(false)
    val isNewInfo = mutableStateOf(false)

    private fun isFullNameValid(): Boolean {
        val numberOfWords = fullName.value.split("\\s+".toRegex())
        return numberOfWords.size > 1 && fullName.value.isNotEmpty() && numberOfWords[numberOfWords.lastIndex].trim()
            .isNotEmpty()
    }

    fun getBackgroundColor(isValid: Boolean): Color {
        return if (isValid) {
            PrimaryLightGrayTransparent
        } else {
            PrimaryLightRed
        }
    }

    fun fieldBorderColor(isValid: Boolean): Color {
        return if (isValid) PrimaryGray else PrimaryRed
    }

    fun showDateErrorMessage(): Boolean {
        return if (dateOfBirth.value.isNotEmpty()) {
            dateOfBirth.value.length < 8
        } else {
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserEntity() {
        viewModelScope.launch(Dispatchers.IO) {
            val userEntity =
                itapeviPrevRepository.getUserByEmail(auth.currentUser?.email.toString())
            try {
                if (userEntity == null) {
                    _profileInfoList.value = arrayListOf(
                        ProfileInfoModel(
                            Icons.Outlined.Email,
                            R.string.email,
                            auth.currentUser?.email.toString()
                        ),
                    )
                    _getUserState.value = GetUserState.UserEntityIsEmpty
                } else {
                    _profileInfoList.value = getProfileInfo(userEntity)
                    _getUserState.value = GetUserState.Complete
                }

            } catch (e: Exception) {
                _getUserState.value = GetUserState.FailedGetUser
            }

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getProfileInfo(userEntity: UserEntity): List<ProfileInfoModel> {
        return arrayListOf(
            ProfileInfoModel(
                Icons.Outlined.Person,
                R.string.name,
                userEntity.fullName.toString()
            ),
            ProfileInfoModel(
                Icons.Outlined.DateRange,
                R.string.date_of_birth,
                formatDateString(userEntity.dateOfBirth.toString())
            ),
            ProfileInfoModel(
                Icons.Outlined.Email,
                R.string.email,
                userEntity.email.toString()
            )
        )
    }

    fun showFullNameErrorMessage(): Boolean {
        return if (fullName.value.isNotEmpty()) {
            !isFullNameValid()
        } else {
            false
        }
    }

    fun updateUserInfo() {
        if (!showFullNameErrorMessage() &&
            dateOfBirth.value.isNotEmpty() &&
            !showDateErrorMessage()
        ) {
            try {
                if (isNewInfo.value) {
                    addNewUser()
                } else {
                    updateUser()
                }

                _getUserState.value = GetUserState.InfoUpdated
            } catch (e: Exception) {
                _getUserState.value = GetUserState.FailedUpdateUser
            }


        }
    }

    fun addNewUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val userEntity = UserEntity(
                dateOfBirth = dateOfBirth.value,
                fullName = fullName.value,
                email = auth.currentUser?.email.toString(),
                identification = 0
            )
            itapeviPrevRepository.addUser(
                userEntity
            )
        }
    }

    fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            itapeviPrevRepository.updateUserInfo(
                dateOfBirth.value,
                fullName.value,
                auth.currentUser?.email.toString()
            )
        }
    }

    fun signOut() {
        auth.signOut()
        if (auth.currentUser == null) _getUserState.value =
            GetUserState.UserSignedOut else _getUserState.value = GetUserState.ErrorSignOut
    }

    fun refreshState() {
        _getUserState.value = GetUserState.Initial
    }
}
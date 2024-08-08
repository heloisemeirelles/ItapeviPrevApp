package itapeviprev.cursoandroid.com.itapeviprev

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itapeviprev.cursoandroid.com.data.constants.Constants.USER_EMAIL

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val userIsLoggedIn = mutableStateOf(false)

    init {
        viewModelScope.launch {
            userIsLoggedIn.value = dataStoreManager.getUserCredential()?.isNotEmpty() == true
        }
    }
}
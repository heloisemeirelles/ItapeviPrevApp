package itapeviprev.cursoandroid.com.itapeviprev

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val userIsLoggedIn = mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()

    init {
        viewModelScope.launch {
            userIsLoggedIn.value =
                dataStoreManager.getRememberUser() == true && auth.currentUser !== null
        }
    }
}
package itapeviprev.cursoandroid.com.itapeviprev.feature.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            dataStoreManager.cleanDataStore()
        }
    }
}
package itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import itapeviprev.cursoandroid.com.data.constants.Constants.USER_EMAIL
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { preference ->
            preference[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun saveUserCredential(value: String) {
        if(value.isNotEmpty()) {
            save(USER_EMAIL, value)
        }
    }

    suspend fun getUserCredential(): String? {
        return read(USER_EMAIL)
    }

    suspend fun cleanDataStore() {
        dataStore.edit { preference ->
            preference.clear()
        }
    }
}
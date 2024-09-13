package itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import itapeviprev.cursoandroid.com.data.constants.Constants.CPF
import itapeviprev.cursoandroid.com.data.constants.Constants.PROTOCOL_NUMBER
import itapeviprev.cursoandroid.com.data.constants.Constants.QUERY
import itapeviprev.cursoandroid.com.data.constants.Constants.REMEMBER_USER
import itapeviprev.cursoandroid.com.data.constants.Constants.YEAR
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

    private suspend fun save(key: String, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        dataStore.edit { preference ->
            preference[dataStoreKey] = value
        }
    }

    private suspend fun readBoolean(key: String): Boolean? {
        val dataStoreKey = booleanPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun rememberUser(value: Boolean) {
        save(REMEMBER_USER, value)
    }

    suspend fun getRememberUser(): Boolean? {
        return readBoolean(REMEMBER_USER)
    }

    suspend fun saveCpf(value: String) {
        save(CPF, value)
    }

    suspend fun getCpf(): String? {
        return read(CPF)
    }

    suspend fun saveProtocolNumber(value: String) {
        save(PROTOCOL_NUMBER, value)
    }

    suspend fun getProtocolNumber(): String? {
        return read(PROTOCOL_NUMBER)
    }

    suspend fun saveYear(value: String) {
        save(YEAR, value)
    }

    suspend fun getYear(): String? {
        return read(YEAR)
    }

    suspend fun saveSaveQuery(value: Boolean) {
        save(QUERY, value)
    }

    suspend fun getSaveQuery(): Boolean? {
        return readBoolean(QUERY)
    }

    suspend fun clearSaveQuery() {
        saveCpf("")
        saveProtocolNumber("")
        saveYear("")
        saveSaveQuery(false)
    }

    suspend fun cleanDataStore() {
        dataStore.edit { preference ->
            preference.clear()
        }
    }
}
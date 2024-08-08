package itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val USER_PREFERENCE_DATA_STORE = "userPreferenceDataStore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_DATA_STORE)
package itapeviprev.cursoandroid.com.itapeviprev.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.dataStore
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    fun provideDataStorePreference(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Singleton
    @Provides
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager(dataStore)
    }
}
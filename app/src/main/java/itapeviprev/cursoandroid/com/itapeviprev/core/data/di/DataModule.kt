package itapeviprev.cursoandroid.com.itapeviprev.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun itapeviPrevRepository(
        itapeviPrevRepositoryImpl: ItapeviPrevRepositoryImpl
    ): ItapeviPrevRepository
}
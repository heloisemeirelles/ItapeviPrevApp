package itapeviprev.cursoandroid.com.itapeviprev.core.repository

import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.core.database.util.AppDatabase
import javax.inject.Inject

class ItapeviPrevRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : ItapeviPrevRepository {
    override suspend fun saveUserInfo(userEntity: UserEntity) {
        appDatabase.userDao().addUser(userEntity)
    }
}
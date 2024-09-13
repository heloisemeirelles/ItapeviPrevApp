package itapeviprev.cursoandroid.com.itapeviprev.core.repository

import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.core.database.util.AppDatabase
import itapeviprev.cursoandroid.com.itapeviprev.core.network.RetrofitClient
import retrofit2.Response
import javax.inject.Inject

class ItapeviPrevRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : ItapeviPrevRepository {
    override suspend fun saveUserInfo(userEntity: UserEntity) {
        appDatabase.userDao().addUser(userEntity)
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return appDatabase.userDao().getAll()
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return appDatabase.userDao().findByEmail(email)
    }

    override suspend fun updateUserInfo(dateOfBirth: String, fullName: String, email: String) {
        appDatabase.userDao().updateSelectedFundName(dateOfBirth, fullName, email)
    }

    override suspend fun addUser(userEntity: UserEntity) {
        appDatabase.userDao().addUser(userEntity)
    }

    override suspend fun getProcessInfo(
        process: String,
        cpf: String,
        year: String
    ): Response<String> {
        return RetrofitClient.apiService.situationQuery(process = process, year = year, cpf = cpf)
    }
}
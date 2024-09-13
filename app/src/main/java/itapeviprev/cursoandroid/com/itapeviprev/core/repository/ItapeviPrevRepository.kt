package itapeviprev.cursoandroid.com.itapeviprev.core.repository

import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import retrofit2.Response

interface ItapeviPrevRepository {
    suspend fun saveUserInfo(userEntity: UserEntity)

    suspend fun getAllUsers(): List<UserEntity>

    suspend fun getUserByEmail(email: String): UserEntity?

    suspend fun updateUserInfo(
        dateOfBirth: String,
        fullName: String,
        email: String
    )

    suspend fun addUser(
        userEntity: UserEntity
    )

    suspend fun getProcessInfo(
        process: String,
        cpf: String,
        year: String
    ): Response<String>

}
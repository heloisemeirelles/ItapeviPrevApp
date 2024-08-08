package itapeviprev.cursoandroid.com.itapeviprev.core.repository

import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity

interface ItapeviPrevRepository {
    suspend fun saveUserInfo(userEntity: UserEntity)
}
package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.domain

import itapeviprev.cursoandroid.com.itapeviprev.common.Errors
import itapeviprev.cursoandroid.com.itapeviprev.common.Resource
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val itapeviPrevRepository: ItapeviPrevRepository
) {
    suspend operator fun invoke(userEntity: UserEntity): Flow<Resource<Unit>> =
        flow {
            try {
                emit(Resource.Loading())
                itapeviPrevRepository.saveUserInfo(userEntity)
                emit(Resource.Success(Unit))
            } catch (e: HttpException) {
                emit(Resource.Error(Errors.UnexpectedError.name))
            } catch (e: IOException) {
                emit(Resource.Error(Errors.ServerError.name))
            }
        }
}

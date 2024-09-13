package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.domain

import itapeviprev.cursoandroid.com.itapeviprev.common.Errors
import itapeviprev.cursoandroid.com.itapeviprev.common.Resource
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProcessInfoUseCase @Inject constructor(
    private val itapeviPrevRepository: ItapeviPrevRepository
) {
    suspend operator fun invoke(
        process: String,
        cpf: String,
        year: String
    ): Flow<Resource<String?>> = flow {
        try {
            emit(Resource.Loading())
            val response =
                itapeviPrevRepository.getProcessInfo(process = process, cpf = cpf, year = year)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                emit(Resource.Error(Errors.UnexpectedError.name))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(Errors.UnexpectedError.name))
        } catch (e: IOException) {
            emit(Resource.Error(Errors.ServerError.name))
        }
    }
}
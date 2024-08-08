package itapeviprev.cursoandroid.com.itapeviprev.feature.signup.domain


import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import itapeviprev.cursoandroid.com.itapeviprev.common.Errors
import itapeviprev.cursoandroid.com.itapeviprev.common.FirebaseStatus
import itapeviprev.cursoandroid.com.itapeviprev.common.Resource
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity
import itapeviprev.cursoandroid.com.itapeviprev.core.repository.ItapeviPrevRepository
import itapeviprev.cursoandroid.com.itapeviprev.feature.login.ui.LoginScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val itapeviPrevRepository: ItapeviPrevRepository
) {
    suspend operator fun invoke(userEntity: UserEntity, password: String?): Flow<Resource<Unit>> =
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

fun createAccount(email: String?, password: String?): FirebaseStatus {
    val firebaseAuth: FirebaseAuth = Firebase.auth
    var firebaseStatus = FirebaseStatus.Initial
    firebaseAuth.createUserWithEmailAndPassword(email.toString(), password.toString())
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseStatus = FirebaseStatus.SuccessfulAuthentication
            } else {
                with(task.exception.toString()) {
                    firebaseStatus = when (true) {
                        this.contains(FirebaseStatus.FirebaseAuthWeakPasswordException.name) -> {
                            FirebaseStatus.FirebaseAuthWeakPasswordException
                        }

                        else -> {
                            FirebaseStatus.GenericError
                        }
                    }
                }

            }
        }
    return firebaseStatus
}


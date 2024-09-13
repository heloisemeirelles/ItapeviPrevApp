package itapeviprev.cursoandroid.com.itapeviprev.core.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("consulta/consulta.php")
    suspend fun situationQuery(
        @Field("consulta-completa") method: String = "Consultar",
        @Field("numero") process: String,
        @Field("ano") year: String,
        @Field("cpf") cpf: String
    ): Response<String>
}
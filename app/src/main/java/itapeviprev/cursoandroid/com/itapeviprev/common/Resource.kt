package itapeviprev.cursoandroid.com.itapeviprev.common

sealed class Resource<T>(
    val data: T? = null,
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(val message: String, data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils

sealed class PaymentForecastState {
    data object Initial : PaymentForecastState()
    data object Loading : PaymentForecastState()
    data object Complete : PaymentForecastState()
    data class Error(val error: String) : PaymentForecastState()
}
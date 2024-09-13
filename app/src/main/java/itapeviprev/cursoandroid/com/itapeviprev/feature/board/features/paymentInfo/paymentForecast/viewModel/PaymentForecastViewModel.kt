package itapeviprev.cursoandroid.com.itapeviprev

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.Months.APRIL
import itapeviprev.cursoandroid.com.itapeviprev.Months.AUGUST
import itapeviprev.cursoandroid.com.itapeviprev.Months.FEBRUARY
import itapeviprev.cursoandroid.com.itapeviprev.Months.JANUARY
import itapeviprev.cursoandroid.com.itapeviprev.Months.JULY
import itapeviprev.cursoandroid.com.itapeviprev.Months.JUNE
import itapeviprev.cursoandroid.com.itapeviprev.Months.MARCH
import itapeviprev.cursoandroid.com.itapeviprev.Months.MAY
import itapeviprev.cursoandroid.com.itapeviprev.Months.SEPTEMBER
import itapeviprev.cursoandroid.com.itapeviprev.common.Errors
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.paymentInfo.paymentForecast.utils.PaymentForecastState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class FirebasePaymentModel(
    val mês: String = "",
    val dia: String = "",
    val situacao: String = ""
)

@HiltViewModel
class PaymentForecastViewModel @Inject constructor(
    private val databaseReference: DatabaseReference
) : ViewModel() {

    private val _paymentForecastState =
        MutableStateFlow<PaymentForecastState>(PaymentForecastState.Initial)
    val paymentForecastState: StateFlow<PaymentForecastState> = _paymentForecastState

    val paymentList = mutableListOf(
        mutableListOf<FirebasePaymentModel?>(),
        mutableListOf<FirebasePaymentModel?>(),
        mutableListOf<FirebasePaymentModel?>(),
        mutableListOf<FirebasePaymentModel?>()
    )

    fun refreshState() {
        _paymentForecastState.value = PaymentForecastState.Initial
    }

    fun fetchPaymentData() {

        val listener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _paymentForecastState.value = PaymentForecastState.Loading

                dataSnapshot.children.forEach { snapshot ->
                    val payment = snapshot.getValue(FirebasePaymentModel::class.java)

                    when (snapshot.key) {
                        JANUARY, FEBRUARY, MARCH -> {
                            paymentList[0].add(payment)
                        }

                        APRIL, MAY, JUNE -> {
                            paymentList[1].add(payment)
                        }

                        JULY, AUGUST, SEPTEMBER -> {
                            paymentList[2].add(payment)
                        }

                        else -> {
                            paymentList[3].add(payment)
                        }
                    }
                }

                _paymentForecastState.value = PaymentForecastState.Complete
            }

            override fun onCancelled(databaseError: DatabaseError) {
                _paymentForecastState.value = PaymentForecastState.Error(databaseError.message)
            }
        }

        if (_paymentForecastState.value != PaymentForecastState.Complete &&
            _paymentForecastState.value != PaymentForecastState.Loading &&
            _paymentForecastState.value != PaymentForecastState.Initial
        ) {
            _paymentForecastState.value = PaymentForecastState.Error(Errors.UnexpectedError.name)
        }
        databaseReference.addListenerForSingleValueEvent(listener)
    }
}

object PaymentSituation {
    const val PAID = "PAGO"
    const val IN_CLOSING = "EM FECHAMENTO"
}

object Months {
    const val JANUARY = "01"
    const val FEBRUARY = "02"
    const val MARCH = "03"
    const val APRIL = "04"
    const val MAY = "05"
    const val JUNE = "06"
    const val JULY = "07"
    const val AUGUST = "08"
    const val SEPTEMBER = "09"
}

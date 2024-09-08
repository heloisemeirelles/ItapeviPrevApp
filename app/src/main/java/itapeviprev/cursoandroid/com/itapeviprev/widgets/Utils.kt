package itapeviprev.cursoandroid.com.itapeviprev.widgets


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun emailIsValid(email: String): Boolean {
    val emailRegex = Regex("^[\\w-.+]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    return email.matches(emailRegex)
}

fun formatDateInput(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]

        if (i % 2 == 1 && i <= 4) out += "/"
    }

    val creditCardOffsetTranslator =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 1
                if (offset <= 7) return offset + 2
                return 10
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset
                if (offset <= 10) return offset - 2


                return 7
            }
        }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

fun decimalFormat(value: Double): String {
    return DecimalFormat("0.00").format(value)
}

@Composable
fun GenericWebView(url: String) {

    val webViewState = rememberWebViewState(url = url)

    WebView(
        state = webViewState,
        modifier = Modifier.fillMaxSize()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateString(date: String): String {
    return if(date.length == 8) {
        val inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val formattedDate = LocalDate.parse(date, inputFormatter)

        formattedDate.format(outputFormatter)
    } else {
        date
    }

}
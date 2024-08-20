package itapeviprev.cursoandroid.com.itapeviprev.widgets

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import java.text.DecimalFormat

fun emailIsValid(email: String): Boolean {
    val emailRegex = Regex("^[\\w-.+]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    return email.matches(emailRegex)
}

fun creditCardFilter(text: AnnotatedString): TransformedText {

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
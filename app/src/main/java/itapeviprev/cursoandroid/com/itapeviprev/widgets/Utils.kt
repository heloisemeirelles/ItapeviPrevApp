package itapeviprev.cursoandroid.com.itapeviprev.widgets


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import itapeviprev.cursoandroid.com.itapeviprev.R
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

@Composable
fun WebViewScreen(url: String) {
    val googleDocsViewerURL = "http://docs.google.com/gview?embedded=true&url=$url"
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        return false // Let WebView handle the URL
                    }
                }
                loadUrl(googleDocsViewerURL)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun openWebPage(uri: Uri?, context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        uri
    )
    context.startActivity(intent)
}
package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.pdfLcl

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.widgets.WebViewScreen

@Composable
fun PdfLclScreen() {
    WebViewScreen(url = stringResource(id = R.string.pdf_lc_url))
}
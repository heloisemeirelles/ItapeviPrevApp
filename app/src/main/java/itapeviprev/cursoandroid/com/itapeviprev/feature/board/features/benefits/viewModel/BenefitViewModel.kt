package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.viewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import javax.inject.Inject

@HiltViewModel
class BenefitViewModel @Inject constructor(): ViewModel() {
    val showText = mutableStateOf(false)

    fun getHideShowButtonTextId(): Int {
        return if(showText.value) R.string.close_text else R.string.open_entire_text
    }

    fun getHideShowButtonIcon(): ImageVector {
        return if(showText.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    }

    fun dialPhoneNumber(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}
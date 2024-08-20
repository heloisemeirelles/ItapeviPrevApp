package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.banners.importantAnnouncement.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import javax.inject.Inject

@HiltViewModel
class ImportantAnnouncementViewModel @Inject constructor() : ViewModel() {
    fun getListOfTextBlocks(): List<TextBlock> {
        return arrayListOf(
            TextBlock(
                R.string.who_needs_to_apply,
                R.string.birthday_person_of_this_month
            ),
            TextBlock(
                R.string.why_is_it_important,
                R.string.to_avoid_benefit_to_be_blocked
            ),
            TextBlock(
                R.string.what_to_do,
                R.string.go_in_your_birth_month
            ),
        )
    }
}


data class TextBlock(
    val titleId: Int,
    val subtitleId: Int
)
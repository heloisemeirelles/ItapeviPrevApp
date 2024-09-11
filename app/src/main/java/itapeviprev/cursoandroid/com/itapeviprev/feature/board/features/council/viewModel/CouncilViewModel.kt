package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.widgets.CouncilOption
import javax.inject.Inject

@HiltViewModel
class CouncilViewModel @Inject constructor() : ViewModel() {
    fun getCouncilOptions(): List<CouncilOption> {
        return arrayListOf(
            CouncilOption(
                R.string.notice,
                R.string.notice_url
            ),
            CouncilOption(R.string.schedule, R.string.schedule_url),
            CouncilOption(R.string.documents_to_apply),
            CouncilOption(
                R.string.promotional_folder,
                R.string.promotional_folder_url
            ),
            CouncilOption(
                R.string.electoral_commission,
                R.string.electoral_commission_url
            ),
            CouncilOption(
                R.string.electoral_sections,
                R.string.electoral_session_url
            ),
            CouncilOption(R.string.result, R.string.result_url)
        )
    }
}
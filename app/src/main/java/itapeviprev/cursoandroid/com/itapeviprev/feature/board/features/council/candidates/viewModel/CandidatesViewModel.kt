package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidateModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidatesFilterOption
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidatesFilterTypes
import javax.inject.Inject

@HiltViewModel
class CandidatesViewModel @Inject constructor() : ViewModel() {
    val selectedId = mutableIntStateOf(CandidatesFilterTypes.ALL_CANDIDATES)

    fun getFilterOptions(): List<CandidatesFilterOption> {
        return arrayListOf(
            CandidatesFilterOption(
                id = CandidatesFilterTypes.ALL_CANDIDATES,
                R.string.all_candidates,
            ),
            CandidatesFilterOption(
                id = CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.administrative_council,
            ),
            CandidatesFilterOption(
                id = CandidatesFilterTypes.FISCAL_CANDIDATES,
                R.string.fiscal_council,
            )
        )
    }

    fun getListOfCandidates(): List<CandidateModel> {
        return arrayListOf(
            CandidateModel(
                CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.carla_queiroz,
                R.string.carla_nickname,
                R.drawable.img_council_carla
            ),
            CandidateModel(
                type = CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.juliana_laguna,
                R.string.juliana_nickname,
                R.drawable.img_council_juliana
            ),
            CandidateModel(
                type = CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.marco_antonio,
                R.string.marco_nickname,
                R.drawable.img_council_marco
            ),
            CandidateModel(
                type = CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.antonia_vieira,
                R.string.antonia_nickname,
                R.drawable.img_council_antonia
            ),
            CandidateModel(
                type = CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES,
                R.string.nubia_morais,
                R.string.nubia_nickname,
                R.drawable.img_council_nubia
            ),
            CandidateModel(
                type = CandidatesFilterTypes.FISCAL_CANDIDATES,
                R.string.giseli_adriana,
                R.string.giseli_nickname,
                R.drawable.img_council_giseli
            ),
            CandidateModel(
                type = CandidatesFilterTypes.FISCAL_CANDIDATES,
                R.string.fabio_abduch,
                R.string.fabio_nickname,
                R.drawable.img_council_fabio
            )
        )
    }

    fun isSelected(id: Int): Boolean {
        return selectedId.intValue == id
    }
}
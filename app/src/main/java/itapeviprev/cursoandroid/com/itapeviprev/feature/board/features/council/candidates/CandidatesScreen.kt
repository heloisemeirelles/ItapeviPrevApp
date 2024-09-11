package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.viewModel.CandidatesViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidateCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidateModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidatesFilterCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.council.candidates.widgets.CandidatesFilterTypes
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon

@Composable
fun CandidatesScreen(
    navController: NavHostController,
    viewModel: CandidatesViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            HeaderWithImageAndIcon(
                imageResId = R.drawable.img_council_header,
                iconTint = Color.White
            ) {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.candidates),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.size(16.dp))

                viewModel.getFilterOptions().forEach { option ->
                    CandidatesFilterCard(option, viewModel.isSelected(option.id)) {
                        viewModel.selectedId.intValue = option.id
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }

                CandidatesList(
                    selectId = viewModel.selectedId.intValue,
                    listOfCandidates = viewModel.getListOfCandidates()
                )
            }
        }
    }
}

@Composable
fun CandidatesList(selectId: Int, listOfCandidates: List<CandidateModel>) {
    if (selectId == CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES || selectId == CandidatesFilterTypes.ALL_CANDIDATES) {
        CandidateSelection(
            R.string.administrative_council,
            listOfCandidates.filter { candidate -> candidate.type == CandidatesFilterTypes.ADMINISTRATIVE_CANDIDATES })
    }

    if (selectId == CandidatesFilterTypes.FISCAL_CANDIDATES || selectId == CandidatesFilterTypes.ALL_CANDIDATES) {
        CandidateSelection(
            R.string.fiscal_council,
            listOfCandidates.filter { candidate -> candidate.type == CandidatesFilterTypes.FISCAL_CANDIDATES })
    }
}

@Composable
fun CandidateSelection(titleId: Int, filter: List<CandidateModel>) {
    Text(
        modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
        text = stringResource(id = titleId),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Medium
    )

    filter.forEach { candidate ->
        CandidateCard(candidate)
    }
}

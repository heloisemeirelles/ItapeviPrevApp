package itapeviprev.cursoandroid.com.itapeviprev.feature.board.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets.BoardSliderData
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.widgets.CardData
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlueDark
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val userIsLoggedIn = mutableStateOf(false)

    init {
        viewModelScope.launch {
            val auth = FirebaseAuth.getInstance()

            userIsLoggedIn.value = auth.currentUser != null
        }
    }

    fun getCardInfo(controllerAction: (String) -> Unit): List<CardData> {
        return listOf(
            CardData(R.drawable.ic_board_benefits, R.string.benefits) {
                controllerAction(
                    BoardNavigationScreens.BenefitsListScreen.name
                )
            },
            CardData(
                R.drawable.ic_board_pdf_lcl,
                R.string.pdf_lcl
            )
            { controllerAction(BoardNavigationScreens.PdfLclScreen.name) },
            CardData(R.drawable.ic_board_payment_info, R.string.payment_info) {
                controllerAction(
                    BoardNavigationScreens.PaymentsInfoScreen.name
                )
            },
            CardData(R.drawable.ic_board_health_fund, R.string.fund_health) {
                controllerAction(BoardNavigationScreens.FundHealthScreen.name)
            },
            CardData(R.drawable.ic_board_the_fund, R.string.the_fund) {
                controllerAction(BoardNavigationScreens.FundScreen.name)
            },
            CardData(R.drawable.ic_board_process_info, R.string.process_information) {
                controllerAction(BoardNavigationScreens.ProcessInfoScreen.name)
            },
            CardData(R.drawable.ic_board_council, R.string.council) {
                controllerAction(BoardNavigationScreens.CouncilScreen.name)
            },
            CardData(R.drawable.ic_board_contact, R.string.contact) {
                controllerAction(BoardNavigationScreens.ContactScreen.name)
            },
        )
    }

    fun getSliderInfo(controllerAction: (String) -> Unit): List<BoardSliderData> {
        return listOf(
            BoardSliderData(
                bannerTextId = R.string.contribution_simulator,
                imageResId = R.drawable.img_board_contribution_simulation,
                backgroundColor = PrimaryBlue,
                onClickAction = { controllerAction(BoardNavigationScreens.ContributionSimulatorScreen.name) }
            ),
            BoardSliderData(
                bannerTextId = R.string.important_announcement,
                imageResId = R.drawable.img_banner_important_announcement,
                backgroundColor = PrimaryBlueDark,
                onClickAction = { controllerAction(BoardNavigationScreens.ImportantAnnouncementScreen.name) }
            )
        )
    }
}
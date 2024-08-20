package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.widgets

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.utils.BenefitType
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.widgets.GenericActionCard
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithOneIcon

@Preview
@Composable
fun BenefitScreenPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderWithOneIcon(icon = painterResource(id = R.drawable.ic_back_arrow)) {
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                    text = stringResource(id = R.string.benefits),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                BenefitsItem().getBenefitsList().forEachIndexed { index, item ->
                    GenericActionCard(item.titleId, item.imageResId) {

                    }
                    if (item != BenefitsItem().getBenefitsList().last()) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }

            }
        }


    }
}

data class BenefitsItem(
    val titleId: Int = 0,
    val imageResId: Int = 0,
    val route: String = "",
    val type: String = ""
) {
    fun getBenefitsList(): List<BenefitsItem> {
        return arrayListOf(
            BenefitsItem(
                titleId = R.string.volunteer_retirement,
                imageResId = R.drawable.img_volunteer_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.VolunteerRetirement.name}",
                type = BenefitType.VolunteerRetirement.name
            ),
            BenefitsItem(
                titleId = R.string.special_retirement_for_professors,
                imageResId = R.drawable.img_professor_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.ProfessorRetirement.name}"
            ),
            BenefitsItem(
                titleId = R.string.disablement_retirement,
                imageResId = R.drawable.img_disablement_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DisablementRetirement.name}"
            ),
            BenefitsItem(
                titleId = R.string.death_pension,
                imageResId = R.drawable.img_death_pension,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DeathPension.name}"
            ),
            BenefitsItem(
                titleId = R.string.mandatory_retirement,
                imageResId = R.drawable.img_mandatory_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.MandatoryRetirement.name}"
            ),
            BenefitsItem(
                R.string.special_retirement_for_people_with_disability,
                imageResId = R.drawable.img_disability_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DisabilityRetirement.name}"
            ),
            BenefitsItem(
                titleId = R.string.document_relation,
                imageResId = R.drawable.img_document_relation,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DocumentRelation.name}"
            )
        )
    }
}
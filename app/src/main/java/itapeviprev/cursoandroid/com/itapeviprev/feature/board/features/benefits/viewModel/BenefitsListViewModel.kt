package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.utils.BenefitType
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.navigation.BoardNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.widgets.GenericCardItem
import javax.inject.Inject

@HiltViewModel
class BenefitsListViewModel @Inject constructor() : ViewModel() {
    fun getBenefitsList(): List<GenericCardItem> {
        return arrayListOf(
            GenericCardItem(
                titleId = R.string.volunteer_retirement,
                imageResId = R.drawable.img_volunteer_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.VolunteerRetirement.name}",
            ),
            GenericCardItem(
                titleId = R.string.special_retirement_for_professors,
                imageResId = R.drawable.img_professor_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.ProfessorRetirement.name}"
            ),
            GenericCardItem(
                titleId = R.string.disablement_retirement,
                imageResId = R.drawable.img_disablement_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DisablementRetirement.name}"
            ),
            GenericCardItem(
                titleId = R.string.death_pension,
                imageResId = R.drawable.img_death_pension,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DeathPension.name}"
            ),
            GenericCardItem(
                titleId = R.string.mandatory_retirement,
                imageResId = R.drawable.img_mandatory_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.MandatoryRetirement.name}"
            ),
            GenericCardItem(
                R.string.special_retirement_for_people_with_disability,
                imageResId = R.drawable.img_disability_retirement,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DisabilityRetirement.name}"
            ),
            GenericCardItem(
                titleId = R.string.document_relation,
                imageResId = R.drawable.img_document_relation,
                route = "${BoardNavigationScreens.BenefitScreen.name}/${BenefitType.DocumentRelation.name}"
            )
        )
    }
}
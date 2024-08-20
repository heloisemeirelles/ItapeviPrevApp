package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.utils

import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.benefits.BenefitTextBlock

enum class BenefitType {
    VolunteerRetirement,
    ProfessorRetirement,
    DisablementRetirement,
    DeathPension,
    MandatoryRetirement,
    DisabilityRetirement,
    DocumentRelation,
}

data class ActionCardData(
    val screenTitleId: Int,
    val headerImageResId: Int,
    val textBlock: List<BenefitTextBlock>
)

data class ActionCard(
    val type: String
) {
    fun getInfo(): ActionCardData {
        when (type) {
            BenefitType.VolunteerRetirement.name -> {
                return ActionCardData(
                    screenTitleId = R.string.volunteer_retirement,
                    headerImageResId = R.drawable.img_volunteer_retirement,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            R.string.volunteer_retirement_title_1,
                            R.string.volunteer_retirement_text_1
                        ),
                        BenefitTextBlock(
                            R.string.volunteer_retirement_title_2,
                            R.string.volunteer_retirement_text_2
                        )
                    )
                )
            }

            BenefitType.ProfessorRetirement.name -> {
                return ActionCardData(
                    screenTitleId = R.string.special_retirement_for_professors,
                    headerImageResId = R.drawable.img_professor_retirement,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            0,
                            R.string.professor_retirement_text_1
                        )
                    )
                )
            }

            BenefitType.DisablementRetirement.name -> {
                return ActionCardData(
                    screenTitleId = R.string.disablement_retirement,
                    headerImageResId = R.drawable.img_disablement_retirement,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            R.string.disablement_retirement_title_1,
                            R.string.disablement_retirement_text_1
                        )
                    )
                )
            }

            BenefitType.DeathPension.name -> {
                return ActionCardData(
                    screenTitleId = R.string.death_pension,
                    headerImageResId = R.drawable.img_death_pension,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            R.string.death_pension_title_1,
                            R.string.death_pension_text_1
                        )
                    )
                )
            }

            BenefitType.DocumentRelation.name -> {
                return ActionCardData(
                    screenTitleId = R.string.document_relation,
                    headerImageResId = R.drawable.img_document_relation,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            R.string.document_relation_title_1,
                            R.string.document_relation_text_1
                        ),
                        BenefitTextBlock(
                            R.string.document_relation_title_2,
                            R.string.document_relation_text_2
                        )
                    )
                )
            }

            BenefitType.MandatoryRetirement.name -> {
                return ActionCardData(
                    screenTitleId = R.string.mandatory_retirement,
                    headerImageResId = R.drawable.img_mandatory_retirement,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            titleId = R.string.mandatory_retirement_title_1,
                            textId = R.string.mandatory_retirement_text_1
                        )
                    )
                )
            }

            else -> {
                return ActionCardData(
                    screenTitleId = R.string.special_retirement_for_people_with_disability,
                    headerImageResId = R.drawable.img_disability_retirement,
                    textBlock = arrayListOf(
                        BenefitTextBlock(
                            0,
                            R.string.coming_soon
                        ),
                    )
                )
            }
        }
    }
}
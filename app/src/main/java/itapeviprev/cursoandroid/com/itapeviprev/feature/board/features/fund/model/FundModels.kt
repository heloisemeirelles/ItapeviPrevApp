package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model

data class FundInfoCardModel(
    val titleId: Int,
    val iconId: Int,
    val listOfTextBlocks: List<TextBlockModel>
)

data class TextBlockModel(
    val titleId: Int,
    val textId: Int
)

enum class FundCardTypes {
    CreationAndLegislation,
    Objective,
    OrganizationStructure
}
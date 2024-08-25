package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.viewModel

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model.FundCardTypes
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model.FundInfoCardModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.model.TextBlockModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactModel
import javax.inject.Inject

@HiltViewModel
class FundViewModel @Inject constructor() : ViewModel() {

    fun getFundCardInfo(type: FundCardTypes): FundInfoCardModel {
        return when (type) {
            FundCardTypes.CreationAndLegislation -> {
                FundInfoCardModel(
                    titleId = R.string.creation_and_legislation,
                    iconId = R.drawable.ic_hammer,
                    listOfTextBlocks = arrayListOf(
                        TextBlockModel(
                            titleId = R.string.year_of_creation,
                            textId = R.string.december_2022
                        ),
                        TextBlockModel(
                            titleId = R.string.current_legislation,
                            textId = R.string.complementary_law
                        )
                    )
                )
            }

            FundCardTypes.Objective -> {
                FundInfoCardModel(
                    titleId = R.string.objective,
                    iconId = R.drawable.ic_star,
                    listOfTextBlocks = arrayListOf(
                        TextBlockModel(
                            titleId = R.string.purpose,
                            textId = R.string.purpose_text
                        ),
                        TextBlockModel(
                            titleId = R.string.benefits,
                            textId = R.string.holders_of_elected_positions
                        )
                    )
                )
            }

            FundCardTypes.OrganizationStructure -> {
                FundInfoCardModel(
                    titleId = R.string.organizational_structure,
                    iconId = R.drawable.ic_structure,
                    listOfTextBlocks = arrayListOf(
                        TextBlockModel(
                            titleId = R.string.administration_council,
                            textId = R.string.seven_members
                        ),
                        TextBlockModel(
                            titleId = R.string.fiscal_council,
                            textId = R.string.three_members
                        ),
                        TextBlockModel(
                            titleId = R.string.investment_council,
                            textId = R.string.three_members
                        )
                    )
                )
            }
        }
    }

    fun getListOfContacts(context: Context): List<ContactModel> {
        return arrayListOf(
            ContactModel(
                iconId = R.drawable.ic_map_purple,
                titleId = R.string.address,
                textId = R.string.itapevi_prev_address,
            ) {
                openMap(context)
            },
            ContactModel(
                iconId = R.drawable.ic_phone_contact,
                titleId = R.string.telephone,
                textId = R.string.phone_number,
            ) {
                dialNumber(context)
            },
            ContactModel(
                iconId = R.drawable.ic_email_purple,
                titleId = R.string.email,
                textId = R.string.itapevi_prev_email,
            ) {
                gotToEmail(context)
            },
            ContactModel(
                iconId = R.drawable.ic_site_purple,
                titleId = R.string.site,
                textId = R.string.itapevi_prev_url,
            ) {
                openWebPage(context)
            },
        )
    }

    private fun openWebPage(context: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(context.getString(R.string.itapevi_prev_url))
        )

        context.startActivity(intent)
    }

    private fun openMap(context: Context) {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(context.getString(R.string.google_maps_url))
            )

            intent.setComponent(
                ComponentName(
                    context.getString(R.string.google_maps_package),
                    context.getString(R.string.google_maps_class_name)
                )
            )
            context.startActivity(intent)
        } catch (ex: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.make_sure_maps_is_installed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun gotToEmail(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("mailto:${context.getString(R.string.itapevi_prev_email)}"))
        context.startActivity(intent)
    }

    private fun dialNumber(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${context.getString(R.string.formatted_phone_number)}")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}


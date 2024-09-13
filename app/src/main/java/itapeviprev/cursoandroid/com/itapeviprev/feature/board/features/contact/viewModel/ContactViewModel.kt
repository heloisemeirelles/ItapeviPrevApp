package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.contact.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactModel
import itapeviprev.cursoandroid.com.itapeviprev.widgets.dialNumber
import itapeviprev.cursoandroid.com.itapeviprev.widgets.gotToEmail
import itapeviprev.cursoandroid.com.itapeviprev.widgets.openMap
import itapeviprev.cursoandroid.com.itapeviprev.widgets.openWebPage
import itapeviprev.cursoandroid.com.itapeviprev.widgets.openWhatsApp
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor() : ViewModel() {

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
            ContactModel(
                iconId = R.drawable.ic_whatsapp,
                titleId = R.string.whatsapp,
                textId = R.string.talk_to_us,
            ) {
                openWhatsApp(context)
            }
        )
    }
}
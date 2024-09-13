package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.common.Constants.CLOSE_TD_TAG
import itapeviprev.cursoandroid.com.itapeviprev.common.Constants.TD_TAG
import itapeviprev.cursoandroid.com.itapeviprev.common.Resource
import itapeviprev.cursoandroid.com.itapeviprev.core.data.datastore.DataStoreManager
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.domain.GetProcessInfoUseCase
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.domain.ProcessInfoState
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.InfoModel
import itapeviprev.cursoandroid.com.itapeviprev.widgets.formatToMask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessInfoViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val getProcessInfoUseCase: GetProcessInfoUseCase
) :
    ViewModel() {
    val protocolNumber = mutableStateOf("")
    val processYear = mutableStateOf("")
    val cpf = mutableStateOf("")
    val saveQuery = mutableStateOf(false)
    val processInfoData = mutableListOf<InfoModel>()

    private val _processInfoState = MutableStateFlow<ProcessInfoState>(ProcessInfoState.Initial)
    val processInfoState: StateFlow<ProcessInfoState> = _processInfoState

    private val _initialInfoState = MutableStateFlow(
        InitialInfoModel()
    )
    val initialInfoState: StateFlow<InitialInfoModel> = _initialInfoState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (dataStoreManager.getSaveQuery() == true) {
                _initialInfoState.value = InitialInfoModel(
                    dataStoreManager.getProtocolNumber().toString(),
                    dataStoreManager.getYear().toString(),
                    dataStoreManager.getCpf().toString()
                )
            }
        }
    }

    fun setInitialInfo() {
        protocolNumber.value = _initialInfoState.value.protocolNumber.toString()
        processYear.value = _initialInfoState.value.year.toString()
        cpf.value = _initialInfoState.value.cpf.toString()
        saveQuery.value = true
        _initialInfoState.value = InitialInfoModel()
    }

    private fun saveQuery() {
        viewModelScope.launch {
            dataStoreManager.saveSaveQuery(saveQuery.value)
            dataStoreManager.saveCpf(cpf.value)
            dataStoreManager.saveProtocolNumber(protocolNumber.value)
            dataStoreManager.saveYear(processYear.value)
        }
    }

    fun getData(
    ) {
        saveQuery()
        viewModelScope.launch(Dispatchers.IO) {
            getProcessInfoUseCase.invoke(
                process = protocolNumber.value,
                year = processYear.value,
                cpf = formatToMask(cpf.value)
            ).collect() { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _processInfoState.value = ProcessInfoState.Loading
                    }

                    is Resource.Success -> {
                        _processInfoState.value = ProcessInfoState.Complete
                        setSituationData(resource.data)
                    }

                    is Resource.Error -> {
                        _processInfoState.value = ProcessInfoState.Error
                    }
                }
            }
        }
    }

    private fun setSituationData(data: String?) {
        val body = data?.replace(CLOSE_TD_TAG, "")?.split(TD_TAG)
        if (body?.size == 1) {
            _processInfoState.value = ProcessInfoState.NoDataFound
        } else {
            processInfoData.clear()
            processInfoData.addAll(
                arrayListOf(
                    InfoModel(
                        Icons.Outlined.Person,
                        R.string.name,
                        body?.get(NAME_INDEX).toString()
                    ),
                    InfoModel(
                        Icons.Outlined.Call,
                        R.string.attendant,
                        body?.get(ATTENDANT_INDEX).toString()
                    ),
                    InfoModel(
                        Icons.Outlined.Info,
                        R.string.situation,
                        body?.get(SITUATION_INDEX).toString(),
                    ),
                    InfoModel(
                        Icons.Filled.Email,
                        R.string.attendant_message,
                        body?.get(MESSAGE_INDEX).toString(),
                    ),
                    InfoModel(
                        Icons.Outlined.Create,
                        R.string.observation,
                        body?.get(OBSERVATION_INDEX)?.replace("</tr></table>", "").toString()
                    )
                )
            )
        }
    }

    fun isButtonEnabled(): Boolean {
        return processYear.value.isNotEmpty() && processYear.value.length == 4 && protocolNumber.value.isNotEmpty() && cpf.value.isNotEmpty() && cpf.value.length == 11
    }

    fun refreshState() {
        _processInfoState.value = ProcessInfoState.Initial
        _initialInfoState.value = InitialInfoModel()
    }

    companion object {
        const val NAME_INDEX = 1
        const val ATTENDANT_INDEX = 2
        const val SITUATION_INDEX = 3
        const val MESSAGE_INDEX = 4
        const val OBSERVATION_INDEX = 5
    }
}

data class InitialInfoModel(
    val protocolNumber: String? = "",
    val year: String? = "",
    val cpf: String? = ""
)

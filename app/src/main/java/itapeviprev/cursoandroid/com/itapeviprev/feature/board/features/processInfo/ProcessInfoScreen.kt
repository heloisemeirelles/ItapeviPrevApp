package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactCard
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.fund.widgets.ContactModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.domain.ProcessInfoState
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.viewModel.InitialInfoModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.processInfo.viewModel.ProcessInfoViewModel
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlack
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryLightGray
import itapeviprev.cursoandroid.com.itapeviprev.widgets.CustomCircularProgressBar
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ErrorDialog
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithImageAndIcon
import itapeviprev.cursoandroid.com.itapeviprev.widgets.InfoRow
import itapeviprev.cursoandroid.com.itapeviprev.widgets.MaskedVisualTransformation
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedTextField
import itapeviprev.cursoandroid.com.itapeviprev.widgets.dialNumber
import kotlin.system.exitProcess

@Composable
fun ProcessInfoScreen(
    navController: NavHostController,
    viewModel: ProcessInfoViewModel = hiltViewModel()
) {
    val processInfoState by viewModel.processInfoState.collectAsState()
    val initialInfoState by viewModel.initialInfoState.collectAsState()

    val context = LocalContext.current
    val showErrorDialog = remember { mutableStateOf(false) }

    BackHandler {
        viewModel.refreshState()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderWithImageAndIcon(
                imageResId = R.drawable.img_header_process_info,
                iconTint = PrimaryBlack,
                opacity = 0.7f,
                backgroundColor = PrimaryLightGray
            ) {
                navController.popBackStack()
            }

            when (processInfoState) {
                is ProcessInfoState.Loading -> CustomCircularProgressBar()

                is ProcessInfoState.NoDataFound -> {
                    Toast.makeText(
                        context,
                        stringResource(id = R.string.please_check_your_data),
                        Toast.LENGTH_LONG
                    ).show()

                    viewModel.refreshState()
                }

                is ProcessInfoState.Error -> {
                    showErrorDialog.value = true
                    viewModel.refreshState()
                }

                is ProcessInfoState.Complete -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.process_situation),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        viewModel.processInfoData.forEach { item ->
                            InfoRow(profileInfoModel = item)
                        }

                        Text(
                            text = stringResource(id = R.string.in_case_of_doubt),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 24.dp)
                        )
                        ContactCard(contact = ContactModel(
                            iconId = R.drawable.ic_phone_contact,
                            titleId = R.string.telephone,
                            textId = R.string.phone_number,
                        ) {
                            dialNumber(context)
                        })

                    }
                }

                is ProcessInfoState.Initial -> {

                    if (initialInfoState != InitialInfoModel()) {
                        viewModel.setInitialInfo()
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.search_retirement_process),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.size(24.dp))

                        Text(
                            text = stringResource(id = R.string.protocol_number),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        RoundedTextField(
                            text = viewModel.protocolNumber,
                            placeholder = stringResource(id = R.string.enter_protocol_number),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIconId = R.drawable.ic_umbrella,
                            isDecimal = true
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = stringResource(id = R.string.process_year),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        RoundedTextField(
                            text = viewModel.processYear,
                            placeholder = stringResource(id = R.string.enter_process_year),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIconId = R.drawable.ic_calendar,
                            isDecimal = true,
                            maxLength = 4

                        )

                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = stringResource(id = R.string.cpf),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.size(4.dp))

                        RoundedTextField(
                            text = viewModel.cpf,
                            placeholder = stringResource(id = R.string.enter_cpf),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIconId = R.drawable.ic_person,
                            visualTransformation = MaskedVisualTransformation(),
                            maxLength = 11
                        )

                        Spacer(modifier = Modifier.size(32.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.save_query),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Switch(
                                checked = viewModel.saveQuery.value,
                                onCheckedChange = {
                                    viewModel.saveQuery.value = !viewModel.saveQuery.value
                                },
                                colors = SwitchDefaults.colors(
                                    checkedTrackColor = Color.Blue,
                                    checkedThumbColor = Color.White,
                                    uncheckedBorderColor = Color.Blue,
                                    uncheckedTrackColor = Color.White,
                                    uncheckedThumbColor = Color.Blue,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        RoundedButton(
                            backgroundColor = PrimaryBlue,
                            labelColor = Color.White,
                            label = stringResource(
                                id = R.string.query
                            ),
                            enabled = viewModel.isButtonEnabled()
                        ) {
                            viewModel.getData()
                        }

                        if (showErrorDialog.value) {
                            ErrorDialog(onDismissClick = {
                                viewModel.refreshState()
                                showErrorDialog.value = false
                            },
                                onTryAgain = {
                                    showErrorDialog.value = false
                                    viewModel.refreshState()
                                }) {
                                exitProcess(0)
                            }
                        }

                    }
                }
            }

        }
    }
}
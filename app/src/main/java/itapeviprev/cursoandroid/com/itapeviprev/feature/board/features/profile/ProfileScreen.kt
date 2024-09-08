package itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import itapeviprev.cursoandroid.com.itapeviprev.R
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.viewModel.ProfileViewModel
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.DateOfBirthField
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.FullNameField
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.ProfileInfoRow
import itapeviprev.cursoandroid.com.itapeviprev.feature.board.features.profile.widgets.UpdateDataDialog
import itapeviprev.cursoandroid.com.itapeviprev.navigation.AppNavigationScreens
import itapeviprev.cursoandroid.com.itapeviprev.theme.PrimaryBlue
import itapeviprev.cursoandroid.com.itapeviprev.widgets.ErrorDialog
import itapeviprev.cursoandroid.com.itapeviprev.widgets.HeaderWithOneIcon
import itapeviprev.cursoandroid.com.itapeviprev.widgets.RoundedButton
import kotlin.system.exitProcess

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val getUserState by viewModel.getUserState.collectAsState()
    val profileInfoList by viewModel.profileInfoList.collectAsState()
    val showErrorDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    when (getUserState) {
        is GetUserState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = PrimaryBlue,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }

        is GetUserState.Error -> {
        }

        is GetUserState.UserSignedOut -> {
            navController.navigate(AppNavigationScreens.LandingPageScreen.name)
        }

        is GetUserState.ErrorSignOut -> {
        }

        is GetUserState.Initial -> {
            viewModel.getUserEntity()
        }

        is GetUserState.Complete -> {
            viewModel.isNewInfo.value = false
        }

        is GetUserState.UserEntityIsEmpty -> {
            LaunchedEffect(Unit) {
                viewModel.showDialog.value = true
                viewModel.isNewInfo.value = true
            }

        }

        is GetUserState.InfoUpdated -> {
            viewModel.editView.value = false
            Toast.makeText(context, "Atualizado", Toast.LENGTH_LONG).show()
            viewModel.refreshState()
        }

        is GetUserState.GenericError, GetUserState.FailedUpdateUser, GetUserState.FailedGetUser -> {
            LaunchedEffect(Unit) {
                showErrorDialog.value = true
            }
        }

    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HeaderWithOneIcon(icon = painterResource(id = R.drawable.ic_back_arrow)) {
                    if (viewModel.editView.value) viewModel.editView.value =
                        false else navController.popBackStack()
                }
            }

            val scrollState = rememberScrollState()
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(88.dp),
                            painter = painterResource(id = R.drawable.ic_board_council),
                            contentDescription = ""
                        )
                    }


                    if (viewModel.editView.value) {
                        Spacer(modifier = Modifier.size(24.dp))
                        FullNameField(
                            fullName = viewModel.fullName,
                            fullNameHasFocus = viewModel.fullNameHasFocus,
                            showErrorMessage = viewModel.showFullNameErrorMessage(),
                            backgroundColor = viewModel.getBackgroundColor(!viewModel.showFullNameErrorMessage()),
                            borderColor = viewModel.fieldBorderColor(!viewModel.showFullNameErrorMessage())
                        )

                        DateOfBirthField(
                            viewModel.dateOfBirth,
                            viewModel.dateOfBirthHasFocus,
                            viewModel.fieldBorderColor(!viewModel.showDateErrorMessage()),
                            viewModel.getBackgroundColor(!viewModel.showDateErrorMessage()),
                            viewModel.showDateErrorMessage()
                        )
                    } else {
                        profileInfoList.forEach {
                            ProfileInfoRow(profileInfoModel = it)
                        }
                    }
                }

                if (viewModel.showDialog.value && getUserState == GetUserState.UserEntityIsEmpty) {
                    UpdateDataDialog(closeDialog = {
                        viewModel.showDialog.value = false
                        viewModel.editView.value = false
                    }) {
                        viewModel.showDialog.value = false
                        viewModel.editView.value = true
                    }
                }

                if (showErrorDialog.value) {
                    ErrorDialog(
                        onDismissClick = { showErrorDialog.value = false },
                        onTryAgain = {
                            showErrorDialog.value = false
                            manageTryAgainClick(
                                viewModel,
                                getUserState
                            ) { navController.popBackStack() }
                        }) {
                        if (context is Activity) {
                            (context).finishAffinity()
                        } else {
                            exitProcess(0)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                RoundedButton(
                    backgroundColor = PrimaryBlue,
                    labelColor = Color.White,
                    label = stringResource(
                        id = if (viewModel.editView.value) R.string.save_my_data else R.string.edit_my_data
                    )
                ) {
                    if (viewModel.editView.value) {
                        viewModel.updateUserInfo()

                    } else {
                        viewModel.editView.value = true
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))
                RoundedButton(
                    backgroundColor = Color.White,
                    labelColor = PrimaryBlue,
                    label = stringResource(
                        id = R.string.leave_the_app
                    ),
                    borderColor = PrimaryBlue
                ) {
                    viewModel.signOut()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun manageTryAgainClick(
    viewModel: ProfileViewModel,
    getUserState: GetUserState,
    backToPrevious: () -> Unit
) {
    when (getUserState) {
        is GetUserState.FailedGetUser -> {
            viewModel.getUserEntity()
        }

        is GetUserState.FailedUpdateUser -> {
            viewModel.updateUserInfo()
        }

        else -> {
            backToPrevious()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}
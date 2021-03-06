package com.example.gifter_single_module.profile.profile_add_edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.components.ErrorMessageText
import com.example.gifter_single_module.profile.profile_add_edit.presentation.ProfileAddEditEvent
import com.example.gifter_single_module.profile.profile_add_edit.presentation.ProfileAddEditViewModel
import com.example.gifter_single_module.profile.profile_detail.presentation.event.UiEvent
import com.example.gifter_single_module.profile.profile_detail.util.DateTransformation
import com.example.gifter_single_module.profile.profile_detail.util.DayMonthTransformation
import com.example.gifter_single_module.profile.util.MaxChars
import com.example.gifter_single_module.util.routs.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditProfileScreen(
    viewModel: ProfileAddEditViewModel = hiltViewModel(),
    onLaunch: (String) -> Unit
) {

    val profileNameState = viewModel.profileName.value
    val profileBirthdayDateState = viewModel.profileBirthdayDate.value
    val profileNamedayDateState = viewModel.profileNamedayDate.value
    val textError = viewModel.profileTextError.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
                is UiEvent.SaveProfile -> {
                    onLaunch(Screen.ProfileListScreen.route)
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ProfileAddEditEvent.SaveProfile)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save profile")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                OutlinedTextField(
                    value = profileNameState.text,
                    placeholder = { Text(text = profileNameState.hint) },
                    onValueChange = {
                        if (it.length <= MaxChars.name) {
                            viewModel.onEvent(ProfileAddEditEvent.EnteredProfileName(it))
                        }
                    },
                    isError = textError.name.isError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Name") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileNameState.text.length.toString() + "/" + MaxChars.name.toString())
                    }
                )
                ErrorMessageText(
                    isError = textError.name.isError,
                    errorMessage = textError.name.errorMessage
                )

                OutlinedTextField(
                    value = profileBirthdayDateState.text,
                    placeholder = { Text(text = profileBirthdayDateState.hint) },
                    onValueChange = {
                        if (it.length <= MaxChars.birthdayDate) {
                            viewModel.onEvent(ProfileAddEditEvent.EnteredProfileBirthdayDate(it))
                        }
                    },
                    isError = textError.birthdayDate.isError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Birthday Date") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileBirthdayDateState.text.length.toString() + "/" + MaxChars.birthdayDate.toString())
                    },
                    visualTransformation = DateTransformation()
                )
                ErrorMessageText(
                    isError = textError.birthdayDate.isError,
                    errorMessage = textError.birthdayDate.errorMessage
                )

                OutlinedTextField(
                    value = profileNamedayDateState.text,
                    placeholder = { Text(text = profileNamedayDateState.hint) },
                    onValueChange = {
                        if (it.length <= MaxChars.namedayDate) {
                            viewModel.onEvent(ProfileAddEditEvent.EnteredProfileNamedayDate(it))
                        }
                    },
                    isError = textError.name.isError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nameday Date") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileNamedayDateState.text.length.toString() + "/" + MaxChars.namedayDate.toString())
                    },
                    visualTransformation = DayMonthTransformation()
                )
                ErrorMessageText(
                    isError = textError.namedayDate.isError,
                    errorMessage = textError.namedayDate.errorMessage
                )
            }
        }
    }
}

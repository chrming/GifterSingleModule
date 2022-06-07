package com.example.gifter_single_module.profile.profile_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.components.ErrorMessageText
import com.example.gifter_single_module.profile.profile_detail.presentation.ProfileDetailEvent
import com.example.gifter_single_module.profile.profile_detail.presentation.ProfileDetailViewModel
import com.example.gifter_single_module.profile.profile_detail.presentation.UiEvent
import com.example.gifter_single_module.profile.profile_detail.util.DateTransformation
import com.example.gifter_single_module.profile.profile_detail.util.DayMonthTransformation
import com.example.gifter_single_module.profile.util.MaxChars
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditProfileScreen(
    viewModel: ProfileDetailViewModel = hiltViewModel(),
    onLaunch: () -> Unit
) {

    val profileNameState = viewModel.profileName.value
    val profileBirthdayDateState = viewModel.profileBirthdayDate.value
    val profileNamedayDateState = viewModel.profileNamedayDate.value
    val textError = viewModel.textError.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
                is UiEvent.SaveProfile -> {
                    onLaunch()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //viewModel.onEvent(GiftDetailEvent.SaveGift)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save gift")
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
                    placeholder = {Text(text = profileNameState.hint)},
                    onValueChange = {
                        if (it.length <= MaxChars.name) {
                            viewModel.onEvent(ProfileDetailEvent.EnteredProfileName(it))
                        }
                    },
                    isError = textError.nameError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Name") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileNameState.text.length.toString() + "/" + MaxChars.name.toString())
                    }
                )
                ErrorMessageText(
                    isError = textError.nameError,
                    errorMessage = textError.nameErrorMessage
                )

                OutlinedTextField(
                    value = profileBirthdayDateState.text,
                    placeholder = {Text(text = profileBirthdayDateState.hint)},
                    onValueChange = {
                        if (it.length <= MaxChars.birthdayDate) {
                            viewModel.onEvent(ProfileDetailEvent.EnteredProfileBirthdayDate(it))
                        }
                    },
                    isError = textError.birthdayDateError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Birthday Date") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileBirthdayDateState.text.length.toString() + "/" + MaxChars.birthdayDate.toString())
                    },
                    visualTransformation = DateTransformation()
                )
                ErrorMessageText(
                    isError = textError.birthdayDateError,
                    errorMessage = textError.birthdayDateErrorMessage
                )

                OutlinedTextField(
                    value = profileNamedayDateState.text,
                    placeholder = {Text(text = profileNamedayDateState.hint)},
                    onValueChange = {
                        if (it.length <= MaxChars.namedayDate) {
                            viewModel.onEvent(ProfileDetailEvent.EnteredProfileNamedayDate(it))
                        }
                    },
                    isError = textError.nameError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nameday Date") },
                    singleLine = true,
                    trailingIcon = {
                        Text(text = profileNamedayDateState.text.length.toString() + "/" + MaxChars.namedayDate.toString())
                    },
                    visualTransformation = DayMonthTransformation()
                )
                ErrorMessageText(
                    isError = textError.namedayDateError,
                    errorMessage = textError.namedayDateErrorMessage
                )
            }
        }
    }
}

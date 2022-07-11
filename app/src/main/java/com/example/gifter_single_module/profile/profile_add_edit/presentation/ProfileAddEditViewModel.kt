package com.example.gifter_single_module.profile.profile_add_edit.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.model.InvalidProfileException
import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_add_edit.use_case.ProfileAddEditUseCaseWrapper
import com.example.gifter_single_module.profile.profile_detail.presentation.event.UiEvent
import com.example.gifter_single_module.profile.profile_detail.presentation.state.ProfileTextFieldState
import com.example.gifter_single_module.profile.util.ProfileTextError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileAddEditViewModel @Inject constructor(
    private val profileUseCase: ProfileAddEditUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _profileName = mutableStateOf(ProfileTextFieldState(hint = "Fritz Zwicky"))
    val profileName = _profileName

    private val _profileBirthdayDate =
        mutableStateOf(ProfileTextFieldState(hint = "14 / 02 / 1974"))
    val profileBirthdayDate = _profileBirthdayDate

    private val _profileNamedayDate =
        mutableStateOf(ProfileTextFieldState(hint = "29 / 11"))
    val profileNamedayDate = _profileNamedayDate


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _profileTextError = mutableStateOf(ProfileTextError())
    val profileTextError = _profileTextError

    var currentProfileId: Int? = null

    init {
        savedStateHandle.get<Int>("profileId")?.let { profileId ->
            if (profileId != -1) {
                Log.d("chm", "AddEdit currentProfileId: $profileId")
                viewModelScope.launch {
                    profileUseCase.getProfile(profileId)?.also { profile ->
                        _profileName.value = profileName.value.copy(
                            text = profile.name,
                            isHintVisible = false
                        )
                        _profileBirthdayDate.value = profileBirthdayDate.value.copy(
                            text = profile.birthdayDate.toString(),
                            isHintVisible = false
                        )
                        profile.namedayDate?.let {
                            _profileNamedayDate.value = profileNamedayDate.value.copy(
                                text =
                                profile.namedayDate.toString(),
                                isHintVisible = false
                            )
                        }
                        currentProfileId = profile.profileId
                    }
                }
            }
        }
    }

    fun onEvent(event: ProfileAddEditEvent) {
        when (event) {
            is ProfileAddEditEvent.EnteredProfileName -> {
                _profileTextError.value = profileTextError.value.copy(
                    name = profileUseCase.validateName(event.value)
                )
                if (_profileTextError.value.name.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _profileTextError.value.name.errorMessage ?: "Invalid name"
                            )
                        )
                    }
                }
                _profileName.value = profileName.value.copy(
                    text = event.value
                )
            }
            is ProfileAddEditEvent.EnteredProfileBirthdayDate -> {
                _profileTextError.value = profileTextError.value.copy(
                    birthdayDate = profileUseCase.validateBirthdayDate(event.value)
                )
                if (_profileTextError.value.birthdayDate.isError) {
                    viewModelScope.launch() {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _profileTextError.value.birthdayDate.errorMessage
                                    ?: "Invalid birthday date"
                            )
                        )
                    }
                }
                _profileBirthdayDate.value = profileBirthdayDate.value.copy(
                    text = event.value
                )
            }
            is ProfileAddEditEvent.EnteredProfileNamedayDate -> {
                _profileTextError.value = profileTextError.value.copy(
                    namedayDate = profileUseCase.validateNamedayDate(event.value)
                )
                if (_profileTextError.value.namedayDate.isError) {
                    viewModelScope.launch() {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _profileTextError.value.namedayDate.errorMessage
                                    ?: "Invalid nameday date"
                            )
                        )
                    }
                }
                _profileNamedayDate.value = profileNamedayDate.value.copy(
                    text = event.value
                )
            }
            ProfileAddEditEvent.SaveProfile -> {
                viewModelScope.launch {
                    try {
                        val result = profileUseCase.validateSaveProfile(profileTextError.value)
                        if (result.isError) {
                            profileUseCase.addEditProfile(
                                Profile(
                                    profileId = currentProfileId,
                                    name = _profileName.value.text,
                                    birthdayDate = if (profileBirthdayDate.value.text == "") -1L else _profileBirthdayDate.value.text.toLong(),
                                    namedayDate = if (profileNamedayDate.value.text == "") null else _profileNamedayDate.value.text.toLong()
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveProfile)
                        }
                    } catch (e: InvalidProfileException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Invalid profile"))
                    }
                }
            }
        }
    }
}
//TODO date validation -> month and day in relation to year and month
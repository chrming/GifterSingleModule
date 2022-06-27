package com.example.gifter_single_module.profile.profile_detail.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.model.InvalidProfileException
import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_detail.use_case.ProfileAddEditUseCaseWrapper
import com.example.gifter_single_module.profile.util.TextError
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

    private val _textError = mutableStateOf(TextError())
    val textError = _textError

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
                val validationResult = profileUseCase.validateName(event.value)
                _textError.value = textError.value.copy(
                    nameError = !validationResult.isSuccess,
                    nameErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.nameError) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _profileName.value = profileName.value.copy(
                    text = event.value
                )
            }
            is ProfileAddEditEvent.EnteredProfileBirthdayDate -> {
                val validationResult = profileUseCase.validateBirthdayDate(event.value)
                _textError.value = textError.value.copy(
                    birthdayDateError = !validationResult.isSuccess,
                    birthdayDateErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.birthdayDateError) {
                    viewModelScope.launch() {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _profileBirthdayDate.value = profileBirthdayDate.value.copy(
                    text = event.value
                )
            }
            is ProfileAddEditEvent.EnteredProfileNamedayDate -> {
                val validationResult = profileUseCase.validateNamedayDate(event.value)
                _textError.value = textError.value.copy(
                    namedayDateError = !validationResult.isSuccess,
                    namedayDateErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.namedayDateError) {
                    viewModelScope.launch() {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _profileNamedayDate.value = profileNamedayDate.value.copy(
                    text = event.value
                )
            }
            ProfileAddEditEvent.SaveProfile -> {
                viewModelScope.launch {
                    try {
                        val result = profileUseCase.validateSaveProfile(textError.value)
                        if (result.isSuccess) {
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
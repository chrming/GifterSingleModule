package com.example.gifter_single_module.profile.profile_detail.presentation

import android.text.format.DateFormat
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.profile_detail.model.InvalidProfileException
import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_detail.use_case.ProfileAddEditUseCaseWrapper
import com.example.gifter_single_module.profile.util.TextError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject


@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
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

    private var currentProfileId: Int? = null

    init {
        savedStateHandle.get<Int>("profileId")?.let { profileId ->
            if (profileId != -1) {
                viewModelScope.launch {
                    profileUseCase.getProfile(profileId)?.also {
                        _profileName.value = profileName.value.copy(
                            text = it.name,
                            isHintVisible = false
                        )
                        _profileBirthdayDate.value = profileBirthdayDate.value.copy(
                            text = DateFormat.format("dd/MM/yyyy", Date(it.birthdayDate))
                                .toString(),
                            isHintVisible = false
                        )
                        _profileNamedayDate.value = profileNamedayDate.value.copy(
                            text = DateFormat.format("dd/MM/yyyy", Date(it.namedayDate)).toString(),
                            isHintVisible = false
                        )
                        currentProfileId = it.profileId
                    }
                }
            }
        }
    }

    fun onEvent(event: ProfileDetailEvent) {
        when (event) {
            is ProfileDetailEvent.EnteredProfileName -> {
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
            is ProfileDetailEvent.EnteredProfileBirthdayDate -> {
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
            is ProfileDetailEvent.EnteredProfileNamedayDate -> {
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
            ProfileDetailEvent.SaveProfile -> {
                viewModelScope.launch {
                    try {
                        profileUseCase.validateSaveProfile(textError.value)
                        profileUseCase.addEditProfile(
                            Profile(
                                profileId = currentProfileId,
                                name = _profileName.value.text,
                                birthdayDate =_profileBirthdayDate.value.text.toLong(),
                                namedayDate = _profileNamedayDate.value.text.toLong()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveProfile)
                    }catch (e: InvalidProfileException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Invalid profile"))
                    }
                }
            }
        }
    }
}
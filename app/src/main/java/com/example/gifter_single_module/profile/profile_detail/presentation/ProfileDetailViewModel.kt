package com.example.gifter_single_module.profile.profile_detail.presentation

import android.text.format.DateFormat
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.profile_list.use_case.ProfileUseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject


@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _profileName = mutableStateOf(ProfileTextFieldState(hint = "Enter Name"))
    val profileName = _profileName

    private val _profileBirthdayDate =
        mutableStateOf(ProfileTextFieldState(hint = "Enter date of birthday"))
    val profileBirthdayDate = _profileBirthdayDate

    private val _profileNamedayDate =
        mutableStateOf(ProfileTextFieldState(hint = "Enter date of nameday"))
    val profileNamedayDate = _profileNamedayDate

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

            }
            is ProfileDetailEvent.EnteredProfileBirthdayDate -> {

            }
            is ProfileDetailEvent.EnteredProfileNamedayDate -> {

            }
        }
    }
}
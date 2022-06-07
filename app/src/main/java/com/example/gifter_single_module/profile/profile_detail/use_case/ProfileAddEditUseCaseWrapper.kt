package com.example.gifter_single_module.profile.profile_detail.use_case

import com.example.gifter_single_module.profile.common.use_case.AddEditProfileUseCase
import com.example.gifter_single_module.profile.common.use_case.GetProfileUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateBirthdayDateUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateNameUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateNamedayDateUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateSaveProfileUseCase

data class ProfileAddEditUseCaseWrapper (
    val getProfile: GetProfileUseCase,
    val addEditProfile: AddEditProfileUseCase,
    val validateName: ValidateNameUseCase,
    val validateBirthdayDate: ValidateBirthdayDateUseCase,
    val validateNamedayDate: ValidateNamedayDateUseCase,
    val validateSaveProfile: ValidateSaveProfileUseCase
)
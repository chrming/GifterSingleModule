package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.common.use_case.AddEditProfileUseCase
import com.example.gifter_single_module.profile.common.use_case.GetProfileUseCase

data class ProfileDetailUseCaseWrapper(
    val getProfileList: GetProfileListUseCase,
    val getProfile: GetProfileUseCase,
    val deleteProfile: DeleteProfileUseCase,
    val addEditProfile: AddEditProfileUseCase,
    val getProfileWithGifts: GetProfileWithGiftsUseCase
)
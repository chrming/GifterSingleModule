package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.common.use_case.AddEditProfileUseCase

data class ProfileListUseCaseWrapper(
    val addEditProfile: AddEditProfileUseCase,
    val deleteProfile: DeleteProfileUseCase,
    val getProfiles: GetProfileListUseCase
)

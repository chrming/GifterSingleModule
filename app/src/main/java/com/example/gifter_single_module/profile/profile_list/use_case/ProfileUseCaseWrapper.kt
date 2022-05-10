package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_list.use_case.*

data class ProfileUseCaseWrapper(
    val getProfiles: GetProfilesUseCase,
    val getProfile: GetProfileUseCase,
    val deleteProfile: DeleteProfileUseCase,
    val addEditProfile: AddEditProfileUseCase,
    val getProfileWithGifts: GetProfileWithGiftsUseCase
)
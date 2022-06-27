package com.example.gifter_single_module.profile.profile_detail.use_case

import com.example.gifter_single_module.profile.common.use_case.GetProfileUseCase

data class ProfileDetailUseCaseWrapper (
    val getProfile: GetProfileUseCase,
    val getWithGifts: GetProfileWithGiftsUseCase
)
package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.repository.ProfileRepository

class DeleteProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profile: Profile) {
        repository.deleteProfile(profile)
    }
}
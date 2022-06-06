package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.InvalidProfileException
import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.repository.ProfileRepository

class AddEditProfileUseCase(
    private val repository: ProfileRepository
) {
    @Throws(InvalidProfileException::class)
    suspend operator fun invoke(profile: Profile) {
        when {
            profile.name.isBlank() -> {
                throw InvalidProfileException("Profiles name cannot be blank.")
            }
            else -> repository.addEditProfile(profile)
        }
    }
}
package com.example.gifter_single_module.profile.common.use_case

import com.example.gifter_single_module.profile.model.InvalidProfileException
import com.example.gifter_single_module.profile.model.Profile
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
            profile.birthdayDate == (-1).toLong() -> {
                throw InvalidProfileException("Profiles name cannot be blank.")
            }
            else -> repository.addEditProfile(profile)
        }
    }
}
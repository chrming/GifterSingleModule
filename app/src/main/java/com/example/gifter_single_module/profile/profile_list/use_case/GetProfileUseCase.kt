package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: Int): Profile? {
        return repository.getProfile(id)
    }
}
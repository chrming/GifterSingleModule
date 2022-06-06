package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.ProfileWithGifts
import com.example.gifter_single_module.profile.repository.ProfileRepository

class GetProfileWithGiftsUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profileId: Int): List<ProfileWithGifts> {
        return repository.getProfileWithGifts(profileId)
    }
}
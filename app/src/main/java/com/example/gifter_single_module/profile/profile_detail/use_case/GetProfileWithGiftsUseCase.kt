package com.example.gifter_single_module.profile.profile_detail.use_case

import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetProfileWithGiftsUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(profileId: Int): Flow<List<Gift>> {
        return repository.getProfileWithGifts(profileId)
    }
}
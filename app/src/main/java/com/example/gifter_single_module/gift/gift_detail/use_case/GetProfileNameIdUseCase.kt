package com.example.gifter_single_module.gift.gift_detail.use_case

import com.example.gifter_single_module.gift.model.ProfileNameId
import com.example.gifter_single_module.gift.repository.GiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProfileNameIdUseCase(
    private val repository: GiftRepository
) {
    operator fun invoke(): Flow<List<ProfileNameId>> {
        return repository.getProfileNameId().map { duets ->
            duets.sortedBy { it.name }
        }
    }
}
package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.repository.ProfileRepository
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProfileListUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(
        profileOrder: ProfileOrder = ProfileOrder.Title(
            OrderType.Descending
        )
    ): Flow<List<Profile>> {
        return repository.getProfiles().map { profiles ->
            when (profileOrder.orderType) {
                is OrderType.Ascending -> {
                    when (profileOrder) {
                        is ProfileOrder.Title -> profiles.sortedBy { it.name.lowercase() }
                        is ProfileOrder.Name -> profiles.sortedBy { it.birthdayDate }
                    }
                }
                else -> {
                    when (profileOrder) {
                        is ProfileOrder.Title -> profiles.sortedBy { it.name.lowercase() }
                        is ProfileOrder.Name -> profiles.sortedBy { it.birthdayDate }
                    }
                }
            }
        }
    }
}
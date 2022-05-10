package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.repository.ProfileRepository
import com.example.gifter_single_module.profile.profile_list.util.ProfilesOrder
import com.example.gifter_single_module.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProfilesUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(
        profilesOrder: ProfilesOrder = ProfilesOrder.Title(
            OrderType.Descending
        )
    ): Flow<List<Profile>> {
        return repository.getProfiles().map { profiles ->
            when (profilesOrder.orderType) {
                is OrderType.Ascending -> {
                    when (profilesOrder) {
                        is ProfilesOrder.Title -> profiles.sortedBy { it.name.lowercase() }
                        is ProfilesOrder.Name -> profiles.sortedBy { it.birthdayDate }
                    }
                }
                else -> {
                    when (profilesOrder) {
                        is ProfilesOrder.Title -> profiles.sortedBy { it.name.lowercase() }
                        is ProfilesOrder.Name -> profiles.sortedBy { it.birthdayDate }
                    }
                }
            }
        }
    }
}
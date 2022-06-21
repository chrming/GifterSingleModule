package com.example.gifter_single_module.profile.profile_list.use_case

import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.profile.repository.ProfileRepository
import com.example.gifter_single_module.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProfileListUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(
        profileOrder: ProfileOrder = ProfileOrder.Name(OrderType.Descending)
    ): Flow<List<Profile>> {
        return repository.getProfiles().map { profiles ->
            when (profileOrder.orderType) {
                is OrderType.Ascending -> {
                    when (profileOrder) {
                        is ProfileOrder.Name -> profiles.sortedBy { it.name }
                        is ProfileOrder.Birthday -> profiles.sortedBy { it.birthdayDate }
                        is ProfileOrder.Nameday -> profiles.sortedBy { it.namedayDate }
                    }
                }
                is OrderType.Descending -> {
                    when (profileOrder) {
                        is ProfileOrder.Name -> profiles.sortedByDescending { it.name }
                        is ProfileOrder.Birthday -> profiles.sortedByDescending { it.birthdayDate }
                        is ProfileOrder.Nameday -> profiles.sortedByDescending { it.namedayDate }
                    }
                }
            }
        }
    }
}
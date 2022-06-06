package com.example.gifter_single_module.profile.profile_detail.presentation

import com.example.gifter_single_module.gift.gift_detail.presentation.GiftDetailEvent
import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder

sealed class ProfileDetailEvent {

    // name
    data class EnteredProfileName(val value: String) : ProfileDetailEvent()

    // birthday date
    data class EnteredProfileBirthdayDate(val value: String): ProfileDetailEvent()

    // nameday date
    data class EnteredProfileNamedayDate(val value: String): ProfileDetailEvent()

    // save profile
    object SaveProfile: ProfileDetailEvent()

}
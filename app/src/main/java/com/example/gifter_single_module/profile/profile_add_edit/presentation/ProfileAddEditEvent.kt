package com.example.gifter_single_module.profile.profile_add_edit.presentation

sealed class ProfileAddEditEvent {

    // name
    data class EnteredProfileName(val value: String) : ProfileAddEditEvent()

    // birthday date
    data class EnteredProfileBirthdayDate(val value: String): ProfileAddEditEvent()

    // nameday date
    data class EnteredProfileNamedayDate(val value: String): ProfileAddEditEvent()

    // save profile
    object SaveProfile: ProfileAddEditEvent()

}
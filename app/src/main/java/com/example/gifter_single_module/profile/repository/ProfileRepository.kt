package com.example.gifter_single_module.profile.repository

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.profile.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfileWithGifts(profileId: Int): Flow<List<Gift>>

    fun getProfiles(): Flow<List<Profile>>

    suspend fun getProfile(profileId: Int): Profile?

    suspend fun addEditProfile(profile: Profile): Unit

    suspend fun deleteProfile(profile: Profile): Unit
}
package com.example.gifter_single_module.profile.repository

import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.model.ProfileWithGifts
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfileWithGifts(profileId: Int): List<ProfileWithGifts>

    fun getProfiles(): Flow<List<Profile>>

    suspend fun getProfile(profileId: Int): Profile?

    suspend fun addEditProfile(profile: Profile): Unit

    suspend fun deleteProfile(profile: Profile): Unit
}
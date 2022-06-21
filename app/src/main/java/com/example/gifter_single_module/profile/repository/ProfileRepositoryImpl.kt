package com.example.gifter_single_module.profile.repository

import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.model.ProfileWithGifts
import com.example.gifter_single_module.profile.data_source.ProfileDao
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val dao: ProfileDao
) : ProfileRepository {
    override suspend fun getProfileWithGifts(profileId: Int): List<ProfileWithGifts> {
        return dao.getProfileGifts(profileId)
    }

    override fun getProfiles(): Flow<List<Profile>> {
        return dao.getProfiles()
    }

    override suspend fun getProfile(profileId: Int): Profile? {
        return dao.getProfile(profileId)
    }

    override suspend fun addEditProfile(profile: Profile) {
        dao.addEditProfile(profile)
    }

    override suspend fun deleteProfile(profile: Profile) {
        dao.deleteProfile(profile)
    }

}
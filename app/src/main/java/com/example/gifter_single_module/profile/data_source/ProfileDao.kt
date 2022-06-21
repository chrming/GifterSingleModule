package com.example.gifter_single_module.profile.data_source

import androidx.room.*
import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.model.ProfileWithGifts
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Transaction
    @Query("SELECT * FROM Profile WHERE profileId = :profileId")
    suspend fun getProfileGifts(profileId: Int): List<ProfileWithGifts>

    @Query("SELECT * FROM Profile WHERE profileId = :id")
    suspend fun getProfile(id:Int): Profile?

    @Query("SELECT * FROM Profile")
    fun getProfiles(): Flow<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditProfile(profile: Profile): Unit

    @Delete
    suspend fun deleteProfile(profile: Profile): Unit
}
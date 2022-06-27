package com.example.gifter_single_module.profile.data_source

import androidx.room.*
import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.profile.model.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Transaction
    @Query("SELECT * FROM gift WHERE ownerId = :profileId")
    fun getProfileGifts(profileId: Int): Flow<List<Gift>>

    @Query("SELECT * FROM profile WHERE profileId = :id")
    suspend fun getProfile(id: Int): Profile?

    @Query("SELECT * FROM profile")
    fun getProfiles(): Flow<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditProfile(profile: Profile): Unit

    @Delete
    suspend fun deleteProfile(profile: Profile): Unit
}
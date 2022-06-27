package com.example.gifter_single_module.gift.repository

import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.model.ProfileNameId
import kotlinx.coroutines.flow.Flow

interface GiftRepository {

    fun getGifts(): Flow<List<Gift>>

    suspend fun getGift(giftId: Int): Gift?

    fun getProfileNameId(): Flow<List<ProfileNameId>>

    suspend fun addEditGift(gift: Gift): Unit

    suspend fun deleteGift(gift: Gift): Unit
}
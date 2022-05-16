package com.example.gifter_single_module.gift.repository

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import kotlinx.coroutines.flow.Flow

interface GiftRepository {

    fun getGifts(): Flow<List<Gift>>

    suspend fun getGift(giftId: Int): Gift?

    suspend fun addEditGift(gift: Gift): Unit

    suspend fun deleteGift(gift: Gift): Unit
}
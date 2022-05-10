package com.example.gifter_single_module.gift.gift_list.repository

import com.example.gifter_single_module.gift.data_source.GiftDao
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import kotlinx.coroutines.flow.Flow

class GiftRepositoryImpl(
    private val dao: GiftDao
) : GiftRepository {
    override fun getGifts(): Flow<List<Gift>> {
        return dao.getGifts()
    }

    override suspend fun getGift(giftId: Int): Gift? {
        return dao.getGift(giftId)
    }

    override suspend fun addEditGift(gift: Gift) {
        dao.addEditGift(gift)
    }

    override suspend fun deleteGift(gift: Gift) {
        dao.deleteGift(gift)
    }
}
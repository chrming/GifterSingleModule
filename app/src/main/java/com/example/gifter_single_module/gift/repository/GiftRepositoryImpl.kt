package com.example.gifter_single_module.gift.repository

import com.example.gifter_single_module.gift.data_source.GiftDao
import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.model.ProfileNameId
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

    override fun getProfileNameId(): Flow<List<ProfileNameId>> {
        return dao.getProfileNameId()
    }

    override suspend fun addEditGift(gift: Gift) {
        dao.addEditGift(gift)
    }

    override suspend fun deleteGift(gift: Gift) {
        dao.deleteGift(gift)
    }
}
package com.example.gifter_single_module.gift.data_source

import androidx.room.*
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import kotlinx.coroutines.flow.Flow

@Dao
interface GiftDao {

    @Query("SELECT * FROM gift WHERE giftId = :id")
    suspend fun getGift(id: Int): Gift?

    @Query("SELECT * FROM gift")
    fun getGifts(): Flow<List<Gift>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditGift(gift: Gift): Unit

    @Delete
    suspend fun deleteGift(gift: Gift): Unit
}
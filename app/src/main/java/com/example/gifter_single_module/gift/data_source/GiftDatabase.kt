package com.example.gifter_single_module.gift.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.profile.profile_detail.model.Profile


@Database(
    entities = [Gift::class, Profile::class],
    version = 1
)
abstract class GiftDatabase : RoomDatabase() {
    abstract val giftDao: GiftDao

    companion object {
        const val DATABASE_NAME = "gift_db"
    }
}
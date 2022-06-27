package com.example.gifter_single_module.gift.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.profile.data_source.ProfileDao
import com.example.gifter_single_module.profile.model.Profile


@Database(
    entities = [Gift::class, Profile::class],
    version = 1
)
abstract class GiftDatabase : RoomDatabase() {
    abstract val giftDao: GiftDao
    abstract val profileDao: ProfileDao

    companion object {
        const val DATABASE_NAME = "gift_db"
    }
}
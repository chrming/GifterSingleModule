package com.example.gifter_single_module.profile.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.profile.model.Profile

@Database(
    entities = [Profile::class, Gift::class],
    version = 1
)
abstract class ProfileDatabase: RoomDatabase() {
    abstract val profileDao: ProfileDao

    companion object {
        const val DATABASE_NAME = "profile_db"
    }
}
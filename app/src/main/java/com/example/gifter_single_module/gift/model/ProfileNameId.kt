package com.example.gifter_single_module.gift.model

import androidx.room.ColumnInfo

data class ProfileNameId(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "profileId") val profileId: Int
)

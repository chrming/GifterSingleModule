package com.example.gifter_single_module.profile.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gifter_single_module.gift.model.Gift

data class ProfileWithGifts(
    @Embedded val profile: Profile,
    @Relation(
        parentColumn = "profileId",
        entityColumn = "ownerId"
    )
    val gifts: List<Gift>
)
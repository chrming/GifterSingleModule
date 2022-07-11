package com.example.gifter_single_module.gift.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gifter_single_module.gift.common.state.ImageState

@Entity
data class Gift(

    @PrimaryKey
    val giftId: Int? = null,

    val ownerId: Int? = null,

    @Embedded
    val image: ImageState,

    val ownerName: String,

    val title: String,

    val description: String,

    val mark: String? = null,

    val price: Float,
)

class InvalidGiftException(message: String): Exception(message)
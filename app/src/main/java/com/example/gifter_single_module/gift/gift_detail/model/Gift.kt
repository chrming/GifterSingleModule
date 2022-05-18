package com.example.gifter_single_module.gift.gift_detail.model

import androidx.room.*

@Entity(
    /*foreignKeys = [
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profileId"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]

     */
)
data class Gift(

    @PrimaryKey
    val giftId: Int? = null,

    val ownerId: Int? = null,

    val ownerName: String,

    val title: String,

    val description: String,

    val mark: String? = null,

    val price: Float,

   // @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //val picture: Bitmap? = null
)

class InvalidGiftException(message: String): Exception(message)
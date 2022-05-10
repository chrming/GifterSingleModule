package com.example.gifter_single_module.profile.profile_detail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Profile(

    @PrimaryKey
    val profileId: Int? = null,

    val name: String,

    val birthdayDate: Long,

    val namedayDate: Long,

    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //val profilePicture: Bitmap? = null
)

class InvalidProfileException(message: String): Exception(message)
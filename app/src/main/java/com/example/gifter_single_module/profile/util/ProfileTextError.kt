package com.example.gifter_single_module.profile.util

import com.example.gifter_single_module.util.TextError

data class ProfileTextError(
    val name: TextError = TextError(),
    val birthdayDate:TextError = TextError(),
    val namedayDate: TextError = TextError(),
)

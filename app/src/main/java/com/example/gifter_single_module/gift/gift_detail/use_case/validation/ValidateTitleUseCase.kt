package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import android.util.Log
import com.example.gifter_single_module.gift.util.MaxChars

class ValidateTitleUseCase {
    operator fun invoke(title: String): Result {
        Log.d("CHM", "'${title}' ")
        if (title.isBlank()) {
            return Result(isSuccess = false, errorMessages = "Title cannot be blank.")
        }
        if (title.length > MaxChars.title)
        {
            return Result(isSuccess = false, errorMessages = "Title can contain ${MaxChars.title} characters.")
        }
        if (title.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Allowed special characters: !?.,-+=_'\""
            )
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}
//TODO String resources
//TODO handling errors

package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.gift_list.util.MaxChars

class ValidateTitleUseCase {
    operator fun invoke(title: String): Result {
        if (title.isEmpty()) {
            return Result(isSuccess = false, errorMessages = "Title cannot be blank.")
        }
        if (title.length > MaxChars.title)
        {
            return Result(isSuccess = false, errorMessages = "Title can contain 20 characters.")
        }
        if (title.contains(regex = Regex("""([^a-zA-Z0-9\s\\!?.,\-+=_"':])+"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Title cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
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

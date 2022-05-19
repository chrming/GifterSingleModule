package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.gift_list.util.MaxChars

class ValidateMarkUseCase {
    operator fun invoke(mark: String): Result {
        if (mark.length > MaxChars.mark)
        {
            return Result(
                isSuccess = false,
                errorMessages = "Mark can contain ${MaxChars.mark} characters."
            )
        }
        if (mark.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
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
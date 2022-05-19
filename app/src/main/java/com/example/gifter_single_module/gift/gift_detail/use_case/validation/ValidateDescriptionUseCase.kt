package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.gift_list.util.MaxChars

class ValidateDescriptionUseCase {
    operator fun invoke(description: String): Result {
        if (description.length > MaxChars.description) {
            return Result(
                isSuccess = false,
                errorMessages = "Description can contain ${MaxChars.description} characters."
            )
        }
        if (description.isBlank() || description.isEmpty()) {
            return Result(
                isSuccess = false,
                errorMessages = "Description cannot be blank or empty."
            )
        }
        if (description.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
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

//^([1-9][0-9]{0,2})?(\.[0-9]?)?$  asdfasdf
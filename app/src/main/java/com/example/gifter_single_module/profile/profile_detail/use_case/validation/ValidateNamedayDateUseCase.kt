package com.example.gifter_single_module.profile.profile_detail.use_case.validation

import com.example.gifter_single_module.profile.util.MaxChars

class ValidateNamedayDateUseCase {
    operator fun invoke(date: String): Result {
        if (date.isBlank()) {
            return Result(isSuccess = false, errorMessages = "Date cannot be blank.")
        }
        if (date.length > MaxChars.namedayDate)
        {
            return Result(isSuccess = false, errorMessages = "Date can contain ${MaxChars.namedayDate} characters.")
        }
        if (date.contains(regex = Regex("""([^\d])+"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Date can contain only digits"
            )
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}
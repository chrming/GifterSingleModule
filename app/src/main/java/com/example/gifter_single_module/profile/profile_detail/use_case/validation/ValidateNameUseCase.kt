package com.example.gifter_single_module.profile.profile_detail.use_case.validation

import com.example.gifter_single_module.profile.util.MaxChars

class ValidateNameUseCase {
    operator fun invoke(name: String): Result {
        if (name.isBlank()) {
            return Result(isSuccess = false, errorMessages = "Name cannot be blank.")
        }
        if (name.length > MaxChars.name)
        {
            return Result(isSuccess = false, errorMessages = "Name can contain ${MaxChars.name} characters.")
        }
        if (name.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
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
// TODO Refactor Move Result to gifter util
// TODO Refactor delete one of the date validation making one date validation using string resources
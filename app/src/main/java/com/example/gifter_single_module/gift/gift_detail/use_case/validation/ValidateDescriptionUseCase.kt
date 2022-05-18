package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateDescriptionUseCase {
    operator fun invoke(description: String): Result {
        if (description.isBlank() || description.isEmpty()) {
            return Result(isSuccess = false, errorMessages = "Description cannot be blank or empty.")
        }
        if (description.contains(regex = Regex("""([^a-zA-Z0-9\s\\!?.,\-+=_"':])+"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Description cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
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
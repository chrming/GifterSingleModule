package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateDescriptionUseCase {
    operator fun invoke(description: String): Result {
        if (description.isBlank() || description.isEmpty()) {
            return Result(successful = false, errorMessages = "Description cannot be blank or empty.")
        }
        if (description.contains(regex = Regex("""^(\w|\s|\d|[\\!?.,\-+=_"':])"""))) {
            return Result(
                successful = false,
                errorMessages = "Description cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
            )
        }
        return Result(successful = true)
    }

    data class Result(
        val successful: Boolean,
        val errorMessages: String? = null
    )
}

//^([1-9][0-9]{0,2})?(\.[0-9]?)?$  asdfasdf
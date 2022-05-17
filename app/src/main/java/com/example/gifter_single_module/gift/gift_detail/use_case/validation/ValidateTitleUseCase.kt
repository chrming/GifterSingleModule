package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateTitleUseCase {
    operator fun invoke(title: String): Result {
        if (title.isBlank() || title.isEmpty()) {
            return Result(successful = false, errorMessages = "Title cannot be blank or empty.")
        }
        if (title.contains(regex = Regex("""^(\w|\s|\d|[\\!?.,\-+=_"':])"""))) {
            return Result(
                successful = false,
                errorMessages = "Title cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
            )
        }
        return Result(successful = true)
    }

    data class Result(
        val successful: Boolean,
        val errorMessages: String? = null
    )
}
//TODO String resources

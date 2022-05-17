package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateMarkUseCase {
    operator fun invoke(mark: String): Result {
        if (mark.contains(regex = Regex("""^(\w|\s|\d|[\\!?.,\-+=_"':])"""))) {
            return Result(
                successful = false,
                errorMessages = "Mark cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
            )
        }
        return Result(successful = true)
    }
    data class Result(
        val successful: Boolean,
        val errorMessages: String? = null
    )
}
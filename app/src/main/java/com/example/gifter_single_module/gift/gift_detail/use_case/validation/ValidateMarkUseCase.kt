package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateMarkUseCase {
    operator fun invoke(mark: String): Result {
        if (mark.contains(regex = Regex("""([^a-zA-Z0-9\s\\!?.,\-+=_"':])+"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Mark cannot contain special characters.\nOnly !?.,-+=_'\" are allowed."
            )
        }
        return Result(isSuccess = true)
    }
    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}
package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import android.util.Log
import com.example.gifter_single_module.gift.util.MaxChars
import com.example.gifter_single_module.util.TextError

class ValidateTitleUseCase {
    operator fun invoke(title: String): TextError {
        Log.d("CHM", "'${title}' ")
        if (title.isBlank()) {
            return TextError(isError = true, errorMessage = "Title cannot be blank.")
        }
        if (title.length > MaxChars.title) {
            return TextError(
                isError = true,
                errorMessage = "Title can contain ${MaxChars.title} characters."
            )
        }
        if (title.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Allowed special characters: !?.,-+=_'\""
            )
        }
        return TextError(isError = false)
    }
}
//TODO String resources
//TODO handling errors

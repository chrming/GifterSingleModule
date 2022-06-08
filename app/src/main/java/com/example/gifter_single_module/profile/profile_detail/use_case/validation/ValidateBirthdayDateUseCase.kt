package com.example.gifter_single_module.profile.profile_detail.use_case.validation

import android.util.Log
import com.example.gifter_single_module.profile.util.Const
import com.example.gifter_single_module.profile.util.MaxChars
import java.text.SimpleDateFormat
import java.util.*

class ValidateBirthdayDateUseCase {
    operator fun invoke(date: String): Result {

        val controlDate = Calendar.getInstance()

        if (date.isBlank()) {
            return Result(isSuccess = false, errorMessages = "Date cannot be blank.")
        }
        if (date.length != MaxChars.birthdayDate) {
            return Result(
                isSuccess = false,
                errorMessages = "Date can contain ${MaxChars.birthdayDate} characters."
            )
        }
        if (date.contains(regex = Regex("""([^\d])+"""))) {
            return ValidateBirthdayDateUseCase.Result(
                isSuccess = false,
                errorMessages = "Date can contain only digits"
            )
        }

        val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val formattedDate = formatter.parse(date)

        if (formattedDate.after(controlDate.time)) {
            return Result(
                isSuccess = false,
                errorMessages = "Invalid date"
            )
        }

        controlDate.set(Calendar.YEAR, Const.MIN_YEAR_OF_BIRTH)
        controlDate.set(Calendar.MONTH, Const.MIN_MONTH_OF_BIRTH)
        controlDate.set(Calendar.DAY_OF_MONTH, Const.MIN_DAY_OF_MONTH)

        if (formattedDate.before(controlDate.time)) {
            return Result(
                isSuccess = false,
                errorMessages = "Invalid date"
            )
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}
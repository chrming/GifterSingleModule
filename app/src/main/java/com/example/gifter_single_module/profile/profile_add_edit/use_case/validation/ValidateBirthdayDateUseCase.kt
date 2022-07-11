package com.example.gifter_single_module.profile.profile_add_edit.use_case.validation

import com.example.gifter_single_module.profile.util.Const
import com.example.gifter_single_module.profile.util.MaxChars
import com.example.gifter_single_module.util.TextError
import java.text.SimpleDateFormat
import java.util.*

class ValidateBirthdayDateUseCase {
    operator fun invoke(date: String): TextError {

        val controlDate = Calendar.getInstance()

        if (date.isBlank()) {
            return TextError(isError = true, errorMessage = "Date cannot be blank.")
        }
        if (date.length != MaxChars.birthdayDate) {
            return TextError(
                isError = true,
                errorMessage = "Date can contain ${MaxChars.birthdayDate} characters."
            )
        }
        if (date.contains(regex = Regex("""([^\d])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Date can contain only digits"
            )
        }

        val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val formattedDate = formatter.parse(date)

        if (formattedDate.after(controlDate.time)) {
            return TextError(
                isError = true,
                errorMessage = "Invalid date"
            )
        }

        controlDate.set(Calendar.YEAR, Const.MIN_YEAR_OF_BIRTH)
        controlDate.set(Calendar.MONTH, Const.MIN_MONTH_OF_BIRTH)
        controlDate.set(Calendar.DAY_OF_MONTH, Const.MIN_DAY_OF_MONTH)

        if (formattedDate.before(controlDate.time)) {
            return TextError(
                isError = true,
                errorMessage = "Invalid date"
            )
        }
        return TextError(isError = false)
    }
}
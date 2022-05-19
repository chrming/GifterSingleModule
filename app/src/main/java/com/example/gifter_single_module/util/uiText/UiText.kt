package com.example.gifter_single_module.util.uiText

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    class StringResource(@StringRes val resId: Int, vararg val args: Any) : UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is StringResource -> stringResource(id = resId, *args)
        }
    }
}

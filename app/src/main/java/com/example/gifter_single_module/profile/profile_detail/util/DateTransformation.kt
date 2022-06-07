package com.example.gifter_single_module.profile.profile_detail.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateTransformation: VisualTransformation {

    // xx/xx/xxxx -> dd/mm/yyyy

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 8) {
            text.text.substring(0..7)
        } else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % 2 == 1 && i <= 3) out += " / "
        }
        val dateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 3
                if (offset <= 7) return offset + 6
                return 14
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 4) return offset - 2
                if (offset <= 9) return offset - 4
                return 8
            }
        }
        return TransformedText(AnnotatedString(out), dateOffsetTranslator)
    }
}
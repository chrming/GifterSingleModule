package com.example.gifter_single_module.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewCustomTextField() {
    CustomTextField(text = "abba", label = "AbbA", onValueChange = {}, onFocusChange = {})
}

@Composable
fun CustomTextField(
    text: String,
//  hint: String,
    label: String,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
//  isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onFocusChange: (FocusState) -> Unit = {}
) {
    val lightBlue = Color(0xffd8e6ff)
    val blue = Color(0xff76a9ff)
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = blue
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            maxLines = maxLines
        )
    }
}

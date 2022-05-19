package com.example.gifter_single_module.gift.gift_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessageText(modifier: Modifier = Modifier, isError: Boolean, errorMessage: String?) {
    if (isError) {
        errorMessage?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    } else {
        Spacer(modifier = modifier.height(8.dp))
    }
}
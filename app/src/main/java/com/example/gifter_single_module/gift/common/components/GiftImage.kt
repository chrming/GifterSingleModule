package com.example.gifter_single_module.gift.common.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gifter_single_module.R
import com.example.gifter_single_module.gift.util.ImageSource
import com.example.gifter_single_module.gift.util.ImageSource.*

@Composable
fun GiftImage(modifier: Modifier = Modifier, sourceType: ImageSource = NONE, source: String? = null) {
    when (sourceType) {
        URL -> {
            AsyncImage(
                modifier = modifier,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(source)
                    .build(),
                contentDescription = "Image of a gift",
            )
        }
        STORAGE -> {//TODO Image from storage
        }
        NONE -> {
            Image(
                painterResource(id = R.drawable.add_picture),
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}
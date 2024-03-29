package com.example.gifter_single_module.gift.gift_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gifter_single_module.gift.common.components.GiftImage
import com.example.gifter_single_module.gift.common.state.ImageState
import com.example.gifter_single_module.gift.model.Gift

@Preview
@Composable
fun PreviewGiftItem() {
    val gift = Gift(
        ownerName = "Owner Name",
        title = "Gift's title",
        description = "Gifts short description",
        price = 20.0f,
        mark = "Mark",
        image = ImageState()
    )
    GiftItem(gift = gift, onClick = {}, onDeleteClick = {})

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GiftItem(
    gift: Gift,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp),
        elevation = 10.dp,
        border = BorderStroke(1.dp, SolidColor(Color.Black)),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .width(IntrinsicSize.Min),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {
                    Text(
                        text = gift.title,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = gift.ownerName,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        gift.mark?.let {
                            Text(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = it,
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Start

                            )
                        }
                        gift.price?.let {
                            Text(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = "$it zł",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                {
                    GiftImage(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(85.dp),
                        sourceType = gift.image.uploadOption,
                        source = gift.image.source
                    )
                }
                Column {
                    IconButton(
                        onClick = onDeleteClick,

                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Gift",
                        )
                    }
                }
            }

        }
    }
}

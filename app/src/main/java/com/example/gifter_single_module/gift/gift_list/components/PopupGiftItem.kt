package com.example.gifter_single_module.gift.gift_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.gifter_single_module.gift.gift_detail.model.Gift


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPopupGiftItem() {
    val gift = Gift(
        ownerName = "Owner Name",
        title = "Gift's title",
        description = "Gifts short description",
        price = 20,
        mark = "Mark"
    )
    PopupGiftItem(gift)
}

@Composable
fun PopupGiftItem(
    gift: Gift,
    modifier: Modifier = Modifier,
) {
    Popup(
        alignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .wrapContentSize()
                .padding(8.dp),
            elevation = 10.dp,
            border = BorderStroke(1.dp, SolidColor(Color.Black)),
            shape = RoundedCornerShape(16.dp)

        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = gift.title,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = gift.ownerName,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = modifier.height(4.dp))


                // Place for a picture
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Gift's image"
                )


                Text(
                    text = gift.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 5,
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
        }
    }
}


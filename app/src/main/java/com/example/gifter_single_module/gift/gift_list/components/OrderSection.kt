package com.example.gifter_single_module.gift.gift_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gifter_single_module.components.DefaultRadioButton
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType

@Preview(showBackground = true)
@Composable
fun OrderSectionPreview() {

    Column {
        OrderSection(onOrderChange = {})
    }
}

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    giftsOrder: GiftsOrder = GiftsOrder.Title(OrderType.Descending),
    onOrderChange: (GiftsOrder) -> Unit
) {
    val switchCheckedState = remember { mutableStateOf(true) }
    var orderType = if (switchCheckedState.value) "Descending" else "Ascending"

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = modifier.weight(1f)) {
            DefaultRadioButton(
                text = "Owner",
                selected = giftsOrder is GiftsOrder.Owner,
                onClick = { onOrderChange(GiftsOrder.Owner(giftsOrder.orderType)) })
            DefaultRadioButton(
                text = "Title",
                selected = giftsOrder is GiftsOrder.Title,
                onClick = { onOrderChange(GiftsOrder.Title(giftsOrder.orderType)) })
            DefaultRadioButton(
                text = "Price",
                selected = giftsOrder is GiftsOrder.Price,
                onClick = { onOrderChange(GiftsOrder.Price(giftsOrder.orderType)) })
        }
        Column(modifier = modifier.weight(1f)) {
            Switch(
                checked = switchCheckedState.value,
                onCheckedChange = {
                    onOrderChange(giftsOrder.copy(giftsOrder.orderType.copy()))
                    switchCheckedState.value = !switchCheckedState.value
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = MaterialTheme.colors.primary,
                    uncheckedTrackColor = MaterialTheme.colors.secondary
                )
            )
            Text(text = orderType, style = MaterialTheme.typography.body1)
        }
    }
}
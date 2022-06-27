package com.example.gifter_single_module.profile.profile_list.components

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
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
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
    profileOrder: ProfileOrder = ProfileOrder.Name(OrderType.Descending),
    onOrderChange: (ProfileOrder) -> Unit,
) {
    val switchCheckedState = remember { mutableStateOf(true) }
    var orderType = if (switchCheckedState.value) "Descending" else "Ascending"

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = modifier.weight(1f)) {
            DefaultRadioButton(
                text = "Name",
                selected = profileOrder is ProfileOrder.Name,
                onClick = { onOrderChange(ProfileOrder.Name(profileOrder.orderType)) })
            DefaultRadioButton(
                text = "Birthday",
                selected = profileOrder is ProfileOrder.Birthday,
                onClick = { onOrderChange(ProfileOrder.Birthday(profileOrder.orderType)) })
            DefaultRadioButton(
                text = "Nameday",
                selected = profileOrder is ProfileOrder.Nameday,
                onClick = { onOrderChange(ProfileOrder.Nameday(profileOrder.orderType)) })
        }
        Column(modifier = modifier.weight(1f)) {
            Switch(
                checked = switchCheckedState.value,
                onCheckedChange = {
                    var newOrder = !profileOrder.orderType
                    switchCheckedState.value = !switchCheckedState.value
                    onOrderChange(profileOrder.copy(newOrder))
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
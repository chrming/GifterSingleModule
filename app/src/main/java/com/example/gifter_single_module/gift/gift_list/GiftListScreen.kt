package com.example.gifter_single_module.gift.gift_list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.gift.gift_list.components.GiftItem
import com.example.gifter_single_module.gift.gift_list.components.OrderSection
import com.example.gifter_single_module.gift.gift_list.components.PopupGiftItem
import com.example.gifter_single_module.gift.gift_list.view_model.GiftListEvent
import com.example.gifter_single_module.gift.gift_list.view_model.GiftListViewModel
import com.example.gifter_single_module.util.routs.Screen

@Composable
fun GiftListScreen(
    viewModel: GiftListViewModel = hiltViewModel(),
    onClickNavigate: (String) -> Unit
) {
    val state = viewModel.giftsState.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickNavigate(Screen.AddEditGiftScreen.route) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add gift")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Gifts", style = MaterialTheme.typography.h4)
                IconButton(
                    onClick = {
                        viewModel.onEvent(GiftListEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Sort"
                    )
                }
                AnimatedVisibility(visible = state.isOrderSectionVisible) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        giftsOrder = state.giftsOrder,
                        onOrderChange = { order ->
                            viewModel.onEvent(GiftListEvent.Order(order))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.gifts) { gift ->
                    GiftItem(
                        gift = gift,
                        onClick = {
                            PopupGiftItem(gift = gift)
                        }
                    )
                }
            }
        }
    }
}
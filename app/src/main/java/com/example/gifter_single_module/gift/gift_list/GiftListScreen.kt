package com.example.gifter_single_module.gift.gift_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gifter_single_module.gift.gift_list.components.OrderSection
import com.example.gifter_single_module.gift.gift_list.components.PopupGiftItem
import com.example.components.routs.Screen
import com.example.gifter_single_module.gift.gift_list.components.GiftItem
import com.example.gifter_single_module.gift.gift_list.view_model.GiftListViewModel
import com.example.gifter_single_module.gift.gift_list.view_model.GiftListEvent

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewGiftListScreen() {
    GiftListScreen(rememberNavController())
}


@Composable
fun GiftListScreen(
    navController: NavController,
    viewModel: GiftListViewModel = hiltViewModel()
) {
    val state = viewModel.giftsState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddEditGiftScreen.route) },
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
                        onOrderChange = {
                            viewModel.onEvent(GiftListEvent.Order(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        giftsOrder = state.giftsOrder
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.gifts) { gift ->
                            GiftItem(
                                modifier = Modifier.clickable { },
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
    }
}
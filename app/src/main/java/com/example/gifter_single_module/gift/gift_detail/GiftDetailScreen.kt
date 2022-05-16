package com.example.gifter_single_module.gift.gift_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.gift.gift_detail.view_model.GiftDetailEvent
import com.example.gifter_single_module.gift.gift_detail.view_model.GiftDetailViewModel
import com.example.gifter_single_module.gift.gift_detail.view_model.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GiftDetailScreen(
    viewModel: GiftDetailViewModel = hiltViewModel(),
    onLaunch: () -> Unit
) {
    val giftTitleState = viewModel.giftTitle.value
    val giftDescriptionState = viewModel.giftDescription.value
    val giftOwnerNameState = viewModel.giftOwnersName.value
    val giftPriceState = viewModel.giftPrice.value
    val giftMarkState = viewModel.giftMark.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {

                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
                is UiEvent.SaveGift -> {
                    onLaunch()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(GiftDetailEvent.SaveGift)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save gift")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                TextField(
                    value = giftTitleState.text,
                    onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredTitle(it)) },
//                        onFocusChange = { viewModel.onEvent(GiftDetailEvent.ChangedTitleFocus(it)) },
                    label = { Text(text = "Title") },
                    placeholder = { Text(text = giftTitleState.hint) },
                    modifier = Modifier.height(50.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = giftOwnerNameState.text,
                    onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredOwnerName(it)) },
//                        onFocusChange = { viewModel.onEvent(GiftDetailEvent.ChangedOwnerNameFocus(it)) },
                    label = { Text(text = "Owner") },
                    placeholder = { Text(text = giftOwnerNameState.hint) },
                    modifier = Modifier.height(30.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = giftDescriptionState.text,
                        onValueChange = {
                            viewModel.onEvent(
                                GiftDetailEvent.EnteredDescription(
                                    it
                                )
                            )
                        },
//                            onFocusChange = {viewModel.onEvent(GiftDetailEvent.ChangedDescriptionFocus(it))},
                        label = { Text(text = "Description") },
                        placeholder = { Text(text = giftDescriptionState.hint) },
//                            modifier = Modifier.weight(0.75f)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = giftMarkState.text,
                    onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredMark(it)) },
//                        onFocusChange = { viewModel.onEvent(GiftDetailEvent.ChangedMarkFocus(it)) },
                    placeholder = { Text(text = giftMarkState.hint) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = giftPriceState.text,
                    onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredPrice(it)) },
                    //onFocusChange = { viewModel.onEvent(GiftDetailEvent.ChangedPriceFocus(it)) },
                    placeholder = { Text(text = giftPriceState.hint) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(0.75f)
                )
                Spacer(modifier = Modifier.width(60.dp))
            }
        }
    }
}

//TODO Design
//TODO Input restrictions
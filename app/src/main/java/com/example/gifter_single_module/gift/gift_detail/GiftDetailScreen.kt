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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.components.CustomTextField
import com.example.gifter_single_module.gift.gift_detail.view_model.GiftDetailEvent
import com.example.gifter_single_module.gift.gift_detail.view_model.GiftDetailViewModel
import com.example.gifter_single_module.gift.gift_detail.view_model.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GiftDetailScreen(
    viewModel: GiftDetailViewModel = hiltViewModel(),
    onLaunch: () -> Unit,
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
                Row {
                    CustomTextField(
                        text = giftTitleState.text,
                        label = "Title",
                        modifier = Modifier.weight(1f),
                        onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredTitle(it)) },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomTextField(
                        text = giftOwnerNameState.text,
                        label = "Owner",
                        modifier = Modifier.weight(1f),
                        onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredOwnerName(it)) },
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    text = giftDescriptionState.text,
                    label = "Description",
                    modifier = Modifier,
                    onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredDescription(it)) },
                    singleLine = false,
                    maxLines = 9
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomTextField(
                        text = giftMarkState.text,
                        label = "Mark",
                        modifier = Modifier.weight(1f),
                        onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredMark(it)) },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomTextField(
                        text = giftPriceState.text,
                        label = "Price",
                        modifier = Modifier.weight(1f),
                        onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredPrice(it)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    )
                }
            }
        }
    }
}

//TODO Design
//TODO Title Input restrictions
//TODO Name Input restrictions
//TODO Description Input restrictions
//TODO Mark Input restrictions
//TODO Price Input restrictions
//TODO Choose ownerName from a list of ownerNames
//TODO Add picture from https://...
package com.example.gifter_single_module.gift.gift_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.gift.gift_detail.presentation.GiftDetailEvent
import com.example.gifter_single_module.gift.gift_detail.presentation.GiftDetailViewModel
import com.example.gifter_single_module.gift.gift_detail.presentation.UiEvent
import com.example.gifter_single_module.gift.gift_list.util.MaxChars
import com.example.gifter_single_module.gift.gift_list.util.TextError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GiftDetailScreen(
    viewModel: GiftDetailViewModel = hiltViewModel(),
    onLaunch: () -> Unit,
) {
    // Temp for building list of ownerNames
    val ownerNames = listOf("Tom", "Adam", "Alice", "Peg", "Al").sorted()

    val giftTitleState = viewModel.giftTitle.value
    val giftDescriptionState = viewModel.giftDescription.value
    val giftOwnerNameState = viewModel.giftOwnersName.value
    val giftPriceState = viewModel.giftPrice.value
    val giftMarkState = viewModel.giftMark.value

    val scaffoldState = rememberScaffoldState()
    var expanded by remember { mutableStateOf(false) }

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
                    OutlinedTextField(
                        value = giftTitleState.text,
                        onValueChange = {
                            if (it.length <= MaxChars.title) {
                                TextError.titleError.value = false
                                viewModel.onEvent(GiftDetailEvent.EnteredTitle(it))
                            } else {
                                TextError.titleError.value = true
                            }
                        },
                        isError = TextError.titleError.value,
                        modifier = Modifier.weight(1f),
                        label = { Text("Title") },
                        singleLine = true,
                        trailingIcon = {
                            Text(text = giftTitleState.text.length.toString() + "/" + MaxChars.title.toString())
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(IntrinsicSize.Min)
                            .width(IntrinsicSize.Min)
                    ) {
                        OutlinedTextField(
                            value = giftOwnerNameState.text,
                            onValueChange = { viewModel.onEvent(GiftDetailEvent.EnteredOwnerName(it)) },
                            readOnly = true,
                            modifier = Modifier.weight(1f),
                            label = { Text("Gift's owner") },
                            isError = TextError.ownerNameError.value,
                            placeholder = { Text(giftOwnerNameState.hint) },
                            trailingIcon = {
                                IconButton(onClick = {
                                    expanded = !expanded
                                    TextError.ownerNameError.value = false
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "List of profiles"
                                    )
                                }
                            }
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.width(IntrinsicSize.Min)
                        ) {
                            ownerNames.forEach() {
                                DropdownMenuItem(onClick = {
                                    viewModel.onEvent(
                                        GiftDetailEvent.EnteredOwnerName(
                                            it
                                        )
                                    )
                                    expanded = false
                                }) {
                                    Text(text = it)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = giftDescriptionState.text,
                    onValueChange = {
                        if (it.length <= MaxChars.description) {
                            TextError.descriptionError.value = false
                            viewModel.onEvent(GiftDetailEvent.EnteredDescription(it))
                        } else {
                            TextError.descriptionError.value = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Description") },
                    maxLines = 10,
                    isError = TextError.descriptionError.value,
                    trailingIcon = {
                        Text(text = giftDescriptionState.text.length.toString() + "/" + MaxChars.description.toString())
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = giftMarkState.text,
                        onValueChange = {
                            if (it.length <= MaxChars.mark) {
                                TextError.markError.value = false
                                viewModel.onEvent(GiftDetailEvent.EnteredMark(it))
                            } else {
                                TextError.markError.value = true
                            }
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text("Mark") },
                        maxLines = 10,
                        isError = TextError.markError.value,
                        trailingIcon = {
                            Text(text = giftMarkState.text.length.toString() + "/" + MaxChars.mark.toString())
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = giftPriceState.text,
                        onValueChange = {
                            viewModel.onEvent(GiftDetailEvent.EnteredPrice(it))
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        isError = TextError.priceError.value,
                        trailingIcon = {
                            Text(text = "zł")
                        }
                    )
                }
            }
        }
    }
}

//TODO Design

//TODO Price Input restrictions
//TODO Add picture from https://...
//TODO SQL Injection
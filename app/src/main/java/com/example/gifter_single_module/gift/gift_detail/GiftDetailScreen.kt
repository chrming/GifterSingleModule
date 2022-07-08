package com.example.gifter_single_module.gift.gift_detail
//TODO Refactor to AddEditGiftScreen and create separate screen GiftDetailScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Storage
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.gifter_single_module.components.ErrorMessageText
import com.example.gifter_single_module.gift.common.components.GiftImage
import com.example.gifter_single_module.gift.gift_detail.presentation.GiftDetailEvent
import com.example.gifter_single_module.gift.gift_detail.presentation.GiftDetailViewModel
import com.example.gifter_single_module.gift.gift_detail.presentation.UiEvent
import com.example.gifter_single_module.gift.util.MaxChars
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GiftDetailScreen(
    viewModel: GiftDetailViewModel = hiltViewModel(),
    onLaunch: () -> Unit,
) {
    val owners = viewModel.ownerList

    val giftTitleState = viewModel.giftTitle.value
    val giftDescriptionState = viewModel.giftDescription.value
    val giftOwnerNameState = viewModel.giftOwnersName.value
    val giftPriceState = viewModel.giftPrice.value
    val giftMarkState = viewModel.giftMark.value
    val giftImageState = viewModel.giftImage.value
    val alertState = viewModel.imageAlert.value //should be handled in a different manner
    val textError = viewModel.textError.value

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
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                GiftImage(
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(GiftDetailEvent.IsAlert(true))
                        },
                    source = giftImageState.source,
                    sourceType = giftImageState.uploadOption
                )
            }

            if (alertState.isAlert) {
                AlertDialog(
                    onDismissRequest = { viewModel.onEvent(GiftDetailEvent.IsAlert(false)) },
                    title = { Text(text = "Image Source") },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Enter URL for Image or choose from your storage:")
                            OutlinedTextField(
                                value = giftImageState.source ?: "",
                                onValueChange = {
                                    viewModel.onEvent(
                                        GiftDetailEvent.EnteredUrl(
                                            it
                                        )
                                    )
                                },
                                isError = textError.urlError,
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text("Image URL") },
                                singleLine = true,
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Storage,
                                        contentDescription = "Storage",
                                        modifier = Modifier.clickable {
                                            //TODO Open storage to search for Image
                                        }
                                    )
                                }
                            )
                            ErrorMessageText(
                                isError = textError.urlError,
                                errorMessage = textError.urlErrorMessage
                            )
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.onEvent(GiftDetailEvent.IsAlert(false))
                            viewModel.onEvent(GiftDetailEvent.SubmitRequest)
                        }) {
                            Text(text = "Submit")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                viewModel.onEvent(GiftDetailEvent.IsAlert(false))
                                viewModel.onEvent(GiftDetailEvent.CanceledRequest)
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
            OutlinedTextField(
                value = giftTitleState.text,
                onValueChange = {
                    viewModel.onEvent(GiftDetailEvent.EnteredTitle(it))
                },
                isError = textError.titleError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Title") },
                singleLine = true,
                trailingIcon = {
                    Text(text = giftTitleState.text.length.toString() + "/" + MaxChars.title.toString())
                }
            )
            ErrorMessageText(
                isError = textError.titleError,
                errorMessage = textError.titleErrorMessage
            )

            Row {
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
                        isError = textError.ownerNameError,
                        placeholder = { Text(giftOwnerNameState.hint) },
                        trailingIcon = {
                            IconButton(onClick = {
                                expanded = !expanded
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
                        owners.forEach() { profile ->
                            DropdownMenuItem(onClick = {
                                viewModel.onEvent(
                                    GiftDetailEvent.EnteredOwnerName(
                                        profile.name
                                    )
                                )
                                viewModel.currentOwnerId = profile.profileId
                                expanded = false
                            }) {
                                Text(text = profile.name)
                            }
                        }
                    }
                    ErrorMessageText(
                        isError = textError.ownerNameError,
                        errorMessage = textError.ownerNameErrorMessage
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = giftMarkState.text,
                        onValueChange = {
                            viewModel.onEvent(GiftDetailEvent.EnteredMark(it))
                        },

                        label = { Text("Mark") },
                        singleLine = true,
                        isError = textError.markError,
                        trailingIcon = {
                            Text(text = giftMarkState.text.length.toString() + "/" + MaxChars.mark.toString())
                        }
                    )
                    ErrorMessageText(
                        isError = textError.markError,
                        errorMessage = textError.markErrorMessage
                    )
                }
            }

            OutlinedTextField(
                value = giftDescriptionState.text,
                onValueChange = {
                    viewModel.onEvent(GiftDetailEvent.EnteredDescription(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                maxLines = 10,
                isError = textError.descriptionError,
                trailingIcon = {
                    Text(text = giftDescriptionState.text.length.toString() + "/" + MaxChars.description.toString())
                }
            )
            ErrorMessageText(
                isError = textError.descriptionError,
                errorMessage = textError.descriptionErrorMessage
            )

            OutlinedTextField(
                value = giftPriceState.text,
                onValueChange = {
                    viewModel.onEvent(GiftDetailEvent.EnteredPrice(it))
                },
                modifier = Modifier.width(150.dp),
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                isError = textError.priceError,
                trailingIcon = {
                    Text(text = "zł")
                }
            )
            ErrorMessageText(
                isError = textError.priceError,
                errorMessage = textError.priceErrorMessage
            )
        }
    }
}

//TODO Design
//TODO Add picture from https://...
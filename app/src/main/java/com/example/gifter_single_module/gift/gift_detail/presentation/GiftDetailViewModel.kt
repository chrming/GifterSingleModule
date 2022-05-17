package com.example.gifter_single_module.gift.gift_detail.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_detail.model.InvalidGiftException
import com.example.gifter_single_module.gift.gift_detail.use_case.GiftDetailUseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftDetailViewModel @Inject constructor(
    private val giftUseCase: GiftDetailUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _giftTitle = mutableStateOf(GiftTextFieldState(hint = "Enter title"))
    val giftTitle = _giftTitle

    private val _giftDescription = mutableStateOf(GiftTextFieldState(hint = "Enter description"))
    val giftDescription = _giftDescription

    private val _giftOwnersName = mutableStateOf(GiftTextFieldState(hint = "Enter owners name"))
    val giftOwnersName = _giftOwnersName

    private val _giftPrice = mutableStateOf(GiftTextFieldState(hint = "Price: zł"))
    val giftPrice = _giftPrice

    private val _giftMark = mutableStateOf(GiftTextFieldState(hint = "Mark: Sony"))
    val giftMark = _giftMark

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var validationState by mutableStateOf(GiftDetailValidationState())

    private var currentGiftId: Int? = null
    private var currentOwnerId: Int? = null

    init {
        savedStateHandle.get<Int>("giftId")?.let { giftId ->
            if (giftId != -1) {
                viewModelScope.launch {
                    giftUseCase.getGift(giftId)?.also {
                        _giftTitle.value = giftTitle.value.copy(
                            text = it.title,
                            isHintVisible = false
                        )
                        _giftDescription.value = giftDescription.value.copy(
                            text = it.description,
                            isHintVisible = false
                        )
                        _giftOwnersName.value = giftOwnersName.value.copy(
                            text = it.ownerName,
                            isHintVisible = false
                        )
                        _giftPrice.value = giftPrice.value.copy(
                            text = it.price.toString(),
                            isHintVisible = false
                        )
                        it.mark?.let { it ->
                            _giftMark.value = giftMark.value.copy(
                                text = it,
                                isHintVisible = false
                            )
                        }
                        currentGiftId = it.giftId
                        currentOwnerId = it.ownerId
                    }
                }
            }
        }
    }

    fun onEvent(event: GiftDetailEvent) {
        when (event) {
            is GiftDetailEvent.EnteredTitle -> {
                _giftTitle.value = giftTitle.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.ChangedTitleFocus -> {
                _giftTitle.value = giftTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && giftTitle.value.text.isBlank()
                )
            }
            is GiftDetailEvent.EnteredDescription -> {
                _giftDescription.value = giftDescription.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.ChangedDescriptionFocus -> {
                _giftDescription.value = giftDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && giftDescription.value.text.isBlank()
                )
            }
            is GiftDetailEvent.EnteredOwnerName -> {
                _giftOwnersName.value = giftOwnersName.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.ChangedOwnerName -> {
                _giftOwnersName.value = giftOwnersName.value.copy(
                    isHintVisible = !event.focusState.isFocused && giftOwnersName.value.text.isBlank()
                )
            }
            is GiftDetailEvent.EnteredPrice -> {
                Log.d("CHM", "Entered price Event started")
                _giftPrice.value = giftPrice.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.ChangedPriceFocus -> {
                _giftPrice.value = giftPrice.value.copy(
                    isHintVisible = !event.focusState.isFocused && giftPrice.value.text.isBlank()
                )
            }
            is GiftDetailEvent.EnteredMark -> {
                _giftMark.value = giftMark.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.ChangedMarkFocus -> {
                _giftMark.value = giftMark.value.copy(
                    isHintVisible = !event.focusState.isFocused && giftMark.value.text.isBlank()
                )
            }
            is GiftDetailEvent.SaveGift -> {
                viewModelScope.launch {
                    try {//TODO Should not be here
                        var price = giftPrice.value.text
                        if (price.isEmpty()) {
                            price = "0"
                        }
                        giftUseCase.addEditGift.invoke(

                            Gift(
                                title = giftTitle.value.text,
                                description = giftDescription.value.text,
                                ownerName = giftOwnersName.value.text,
                                mark = giftMark.value.text,
                                price = price.toInt(),
                                giftId = currentGiftId,
                                ownerId = currentOwnerId
                            )
                            //TODO() Add Owner Id -> getProfileIdByName(ownerName)
                        )
                        _eventFlow.emit(UiEvent.SaveGift)
                    } catch (e: InvalidGiftException) {
                        Log.d("CHM", "Exception caught!")
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Invalid gift"))
                    }
                }
            }
        }
    }
}
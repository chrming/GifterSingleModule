package com.example.gifter_single_module.gift.gift_detail.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.model.InvalidGiftException
import com.example.gifter_single_module.gift.gift_detail.use_case.GiftDetailUseCaseWrapper
import com.example.gifter_single_module.gift.model.ProfileNameId
import com.example.gifter_single_module.gift.util.TextError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO Stacking scaffolds

@HiltViewModel
class GiftDetailViewModel @Inject constructor(
    private val giftUseCase: GiftDetailUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //
    private val _giftTitle = mutableStateOf(GiftTextFieldState(hint = "Enter title"))
    val giftTitle = _giftTitle

    private val _giftDescription = mutableStateOf(GiftTextFieldState(hint = "Enter description"))
    val giftDescription = _giftDescription

    private val _giftOwnersName = mutableStateOf(GiftTextFieldState(hint = "Enter owners name"))
    val giftOwnersName = _giftOwnersName

    private val _giftPrice = mutableStateOf(GiftTextFieldState(text = "0.00", hint = "0.00"))
    val giftPrice = _giftPrice
    //TODO text should be empty, different way of validate price

    private val _giftMark = mutableStateOf(GiftTextFieldState(hint = "Mark: Sony"))
    val giftMark = _giftMark

    private val _ownerList = mutableListOf<ProfileNameId>()
    val ownerList = _ownerList

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _textError = mutableStateOf(TextError())
    val textError = _textError

    private var currentGiftId: Int? = null
    var currentOwnerId: Int? = null

    private var getProfilesJob: Job? = null

    init {
        getProfileNames()
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
                val validationResult = giftUseCase.validateTitle(event.value)
                _textError.value = textError.value.copy(
                    titleError = !validationResult.isSuccess,
                    titleErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.titleError) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _giftTitle.value = giftTitle.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredDescription -> {
                val validationResult = giftUseCase.validateDescription(event.value)
                _textError.value = textError.value.copy(
                    descriptionError = !validationResult.isSuccess,
                    descriptionErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.descriptionError) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _giftDescription.value = giftDescription.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredOwnerName -> { //TODO change to validation from name list
                _giftOwnersName.value = giftOwnersName.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredPrice -> {
                val validationResult = giftUseCase.validatePrice(event.value)
                _textError.value = textError.value.copy(
                    priceError = !validationResult.isSuccess,
                    priceErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.priceError) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _giftPrice.value = giftPrice.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredMark -> {
                val validationResult = giftUseCase.validateMark(event.value)
                _textError.value = textError.value.copy(
                    markError = !validationResult.isSuccess,
                    markErrorMessage = validationResult.errorMessages
                )
                if (_textError.value.markError) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar(validationResult.errorMessages!!))
                    }
                }
                _giftMark.value = giftMark.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.SaveGift -> {
                viewModelScope.launch {
                    try {
                        giftUseCase.validateSaveGift(textError.value)
                        giftUseCase.addEditGift.invoke(
                            Gift(
                                title = giftTitle.value.text,
                                description = giftDescription.value.text,
                                ownerName = giftOwnersName.value.text,
                                mark = giftMark.value.text,
                                price = giftPrice.value.text.toFloat(),
                                giftId = currentGiftId,
                                ownerId = currentOwnerId
                            )
                            //TODO() Add Owner Id -> getProfileIdByName(ownerName)
                        )
                        _eventFlow.emit(UiEvent.SaveGift)
                    } catch (e: InvalidGiftException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Invalid gift"))
                    }
                }
            }
        }
    }

    private fun getProfileNames() {
        getProfilesJob?.cancel()
        getProfilesJob = giftUseCase.getProfileNameId()
            .onEach { nameWithIdDuets ->
                Log.d("chm"," nameWithIdDuets: $nameWithIdDuets")
                _ownerList.clear()
                _ownerList.addAll(nameWithIdDuets)
            }
            .launchIn(viewModelScope)
    }
}
//TODO onFocusChanged - useless for now
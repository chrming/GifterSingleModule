package com.example.gifter_single_module.gift.gift_detail.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.common.state.ImageSource
import com.example.gifter_single_module.gift.common.state.ImageState
import com.example.gifter_single_module.gift.gift_detail.presentation.event.GiftDetailEvent
import com.example.gifter_single_module.gift.gift_detail.presentation.event.UiEvent
import com.example.gifter_single_module.gift.gift_detail.presentation.state.GiftDetailImageAlertState
import com.example.gifter_single_module.gift.gift_detail.presentation.state.GiftTextFieldState
import com.example.gifter_single_module.gift.gift_detail.use_case.GiftDetailUseCaseWrapper
import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.model.InvalidGiftException
import com.example.gifter_single_module.gift.model.ProfileNameId
import com.example.gifter_single_module.gift.util.GiftTextError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO Stacking scaffolds

@HiltViewModel
class GiftDetailViewModel @Inject constructor(
    private val giftUseCase: GiftDetailUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _giftImage = mutableStateOf(ImageState())
    val giftImage = _giftImage

    private val _giftTitle = mutableStateOf(GiftTextFieldState(hint = "Enter title"))
    val giftTitle = _giftTitle

    private val _giftDescription = mutableStateOf(GiftTextFieldState(hint = "Enter description"))
    val giftDescription = _giftDescription

    private val _giftOwnersName = mutableStateOf(GiftTextFieldState(hint = "Enter owners name"))
    val giftOwnersName = _giftOwnersName

    private val _giftPrice = mutableStateOf(GiftTextFieldState(text = "0.00", hint = "0.00"))
    val giftPrice = _giftPrice

    private val _giftMark = mutableStateOf(GiftTextFieldState(hint = "Mark: Sony"))
    val giftMark = _giftMark


    private val _ownerList = mutableListOf<ProfileNameId>()
    val ownerList = _ownerList

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _imageAlert = mutableStateOf(GiftDetailImageAlertState())
    val imageAlert = _imageAlert

    private val _giftTextError = mutableStateOf(GiftTextError())
    val giftTextError = _giftTextError


    private var currentGiftId: Int? = null
    var currentOwnerId: Int? = null

    private var getProfilesJob: Job? = null


    init {
        getProfileNames()
        savedStateHandle.get<Int>("giftId")?.let { giftId ->
            if (giftId != -1) {
                viewModelScope.launch {
                    giftUseCase.getGift(giftId)?.also { gift ->
                        _giftTitle.value = giftTitle.value.copy(
                            text = gift.title,
                            isHintVisible = false
                        )
                        _giftDescription.value = giftDescription.value.copy(
                            text = gift.description,
                            isHintVisible = false
                        )
                        _giftOwnersName.value = giftOwnersName.value.copy(
                            text = gift.ownerName,
                            isHintVisible = false
                        )
                        _giftPrice.value = giftPrice.value.copy(
                            text = gift.price.toString(),
                            isHintVisible = false
                        )
                        gift.mark?.let { it ->
                            _giftMark.value = giftMark.value.copy(
                                text = it,
                                isHintVisible = false
                            )
                        }
                        gift.image.source?.let { source ->
                            _giftImage.value = giftImage.value.copy(
                                uploadOption = ImageSource.URL,
                                source = source
                            )
                        }
                        currentGiftId = gift.giftId
                        currentOwnerId = gift.ownerId
                    }
                }
            }
        }
    }

    fun onEvent(event: GiftDetailEvent) {
        when (event) {
            is GiftDetailEvent.EnteredTitle -> {
                _giftTextError.value = giftTextError.value.copy(
                    title = giftUseCase.validateTitle(event.value)
                )
                if (_giftTextError.value.title.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _giftTextError.value.title.errorMessage ?: "Invalid title"
                            )
                        )
                    }
                }
                _giftTitle.value = giftTitle.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredDescription -> {
                _giftTextError.value = giftTextError.value.copy(
                    description = giftUseCase.validateDescription(event.value)
                )
                if (_giftTextError.value.description.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _giftTextError.value.description.errorMessage
                                    ?: "Invalid description"
                            )
                        )
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
                _giftTextError.value = giftTextError.value.copy(
                    price = giftUseCase.validatePrice(event.value)
                )
                if (_giftTextError.value.price.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _giftTextError.value.price.errorMessage ?: "Invalid price"
                            )
                        )
                    }
                }
                _giftPrice.value = giftPrice.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.EnteredMark -> {
                _giftTextError.value = giftTextError.value.copy(
                    mark = giftUseCase.validateMark(event.value)
                )
                if (_giftTextError.value.mark.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _giftTextError.value.mark.errorMessage ?: "Invalid mark."
                            )
                        )
                    }
                }
                _giftMark.value = giftMark.value.copy(
                    text = event.value
                )
            }
            is GiftDetailEvent.IsAlert -> {
                _imageAlert.value = imageAlert.value.copy(
                    isAlert = event.value
                )
            }
            is GiftDetailEvent.EnteredStoragePath -> TODO()
            is GiftDetailEvent.EnteredUrl -> {
                _giftTextError.value = giftTextError.value.copy(
                    url = giftUseCase.validateUrl(event.value)
                )
                if (_giftTextError.value.url.isError) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                _giftTextError.value.url.errorMessage ?: "Invalid URL"
                            )
                        )
                    }
                }
                _giftImage.value = giftImage.value.copy(
                    uploadOption = ImageSource.URL,
                    source = event.value
                )
            }
            is GiftDetailEvent.CanceledRequest -> {
                _giftImage.value = giftImage.value.copy(
                    uploadOption = ImageSource.NONE,
                    source = null
                )
            }
            is GiftDetailEvent.SubmitRequest -> {
                if (_imageAlert.value.url) {
                    _giftImage.value = giftImage.value.copy(
                        uploadOption = ImageSource.URL
                    )
                }
                if (_imageAlert.value.storage) {
                    _giftImage.value = giftImage.value.copy(
                        uploadOption = ImageSource.STORAGE
                    )
                }
            }
            is GiftDetailEvent.SaveGift -> {
                viewModelScope.launch {
                    try {
                        giftUseCase.validateSaveGift(giftTextError.value)
                        giftUseCase.addEditGift.invoke(
                            Gift(
                                title = giftTitle.value.text,
                                description = giftDescription.value.text,
                                ownerName = giftOwnersName.value.text,
                                mark = giftMark.value.text,
                                price = giftPrice.value.text.toFloat(),
                                giftId = currentGiftId,
                                ownerId = currentOwnerId,
                                image = giftImage.value
                            )
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
                Log.d("chm", " nameWithIdDuets: $nameWithIdDuets")
                _ownerList.clear()
                _ownerList.addAll(nameWithIdDuets)
            }
            .launchIn(viewModelScope)
    }
}
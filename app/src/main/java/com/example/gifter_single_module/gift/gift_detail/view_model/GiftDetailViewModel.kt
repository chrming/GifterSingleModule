package com.example.gifter_single_module.gift.gift_detail.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.use_case.GiftUseCaseWrapper
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftDetailViewModel @Inject constructor(
    private val giftsUseCase: GiftUseCaseWrapper
) : ViewModel() {

    private val _giftsState = mutableStateOf(GiftsState())
    val giftsState = _giftsState

    private var lastDeletedGift: Gift? = null

    private var getGiftsJob: Job? = null

    init {
        getGifts(GiftsOrder.Owner(OrderType.Descending))
    }

    fun onEvent(event: GiftsEvent) {
        when (event) {
            is GiftsEvent.Order -> {
                if (
                    _giftsState.value.giftsOrder::class == event.giftsOrder::class
                    && giftsState.value.giftsOrder.orderType == event.giftsOrder.orderType
                ) {
                    return
                }
                getGifts(event.giftsOrder)
            }
            is GiftsEvent.ToggleOrderSection -> {
                viewModelScope.launch {
                    _giftsState.value = giftsState.value.copy(
                        isOrderSectionVisible = !giftsState.value.isOrderSectionVisible
                    )
                }
            }
            is GiftsEvent.DeleteGift -> {
                viewModelScope.launch {
                    giftsUseCase.deleteGift(event.gift)
                    lastDeletedGift = event.gift
                }
            }
            is GiftsEvent.RestoreGift -> {
                viewModelScope.launch {
                    giftsUseCase.addEditGift(lastDeletedGift ?: return@launch)
                    lastDeletedGift = null
                }
            }
        }
    }

    private fun getGifts(giftsOrder: GiftsOrder) {
        getGiftsJob?.cancel()
        getGiftsJob = giftsUseCase.getGifts(giftsOrder)
            .onEach { gifts ->
                _giftsState.value = giftsState.value.copy(
                    gifts = gifts,
                    giftsOrder = giftsOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
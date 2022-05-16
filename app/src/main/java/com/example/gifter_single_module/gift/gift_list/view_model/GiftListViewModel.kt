package com.example.gifter_single_module.gift.gift_list.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.gift_list.use_case.GiftListUseCaseWrapper
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftListViewModel @Inject constructor(
    private val giftsUseCase: GiftListUseCaseWrapper
) : ViewModel() {

    private val _giftsState = mutableStateOf(GiftListState())
    val giftsState = _giftsState

    private var lastDeletedGift: Gift? = null

    private var getGiftsJob: Job? = null

    init {
        getGifts(GiftsOrder.Owner(OrderType.Descending))
    }

    fun onEvent(event: GiftListEvent) {
        when (event) {
            is GiftListEvent.Order -> {
                if (
                    _giftsState.value.giftsOrder::class == event.giftsOrder::class &&
                    giftsState.value.giftsOrder.orderType == event.giftsOrder.orderType
                ) {
                    return
                }
                getGifts(event.giftsOrder)
            }
            is GiftListEvent.ToggleOrderSection -> {
                viewModelScope.launch {
                    _giftsState.value = giftsState.value.copy(
                        isOrderSectionVisible = !giftsState.value.isOrderSectionVisible
                    )
                }
            }
            is GiftListEvent.DeleteGift -> {
                viewModelScope.launch {
                    giftsUseCase.deleteGift(event.gift)
                    lastDeletedGift = event.gift
                }
            }
            is GiftListEvent.RestoreGift -> {
                viewModelScope.launch {
                    giftsUseCase.addEditGift(lastDeletedGift ?: return@launch)
                    lastDeletedGift = null
                }
            }
        }
    }

    private fun getGifts(giftsOrder: GiftsOrder) {
        Log.d("CHM", "getGifts launched")
        getGiftsJob?.cancel()
        getGiftsJob = giftsUseCase.getGifts(giftsOrder = giftsOrder)
            .onEach { gifts ->
                _giftsState.value = giftsState.value.copy(
                    gifts = gifts,
                    giftsOrder = giftsOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.repository.GiftRepository
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGiftsUseCase(
    private val repository: GiftRepository
) {
    operator fun invoke(
        giftsOrder: GiftsOrder = GiftsOrder.Title(OrderType.Descending)
    ): Flow<List<Gift>> {
        return repository.getGifts().map { gifts ->
            when (giftsOrder.orderType) {
                is OrderType.Ascending -> {
                    when (giftsOrder) {
                        is GiftsOrder.Title -> gifts.sortedBy { it.title.lowercase() }
                        is GiftsOrder.Owner -> gifts.sortedBy { it.ownerName.lowercase() }
                        is GiftsOrder.Price -> gifts.sortedBy { it.price }
                    }
                }
                is OrderType.Descending -> {
                    when (giftsOrder) {
                        is GiftsOrder.Title -> gifts.sortedByDescending { it.title.lowercase() }
                        is GiftsOrder.Owner -> gifts.sortedByDescending { it.ownerName.lowercase() }
                        is GiftsOrder.Price -> gifts.sortedByDescending { it.price }
                    }
                }
            }
        }
    }
}
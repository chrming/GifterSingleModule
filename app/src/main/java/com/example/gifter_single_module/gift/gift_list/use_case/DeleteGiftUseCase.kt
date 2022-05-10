package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.gift_list.repository.GiftRepository
import com.example.gifter_single_module.gift.gift_detail.model.Gift

class DeleteGiftUseCase(
    private val repository: GiftRepository
) {
    suspend operator fun invoke(gift: Gift) {
        repository.deleteGift(gift)
    }
}
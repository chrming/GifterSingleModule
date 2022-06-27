package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.repository.GiftRepository

class DeleteGiftUseCase(
    private val repository: GiftRepository
) {
    suspend operator fun invoke(gift: Gift) {
        repository.deleteGift(gift)
    }
}
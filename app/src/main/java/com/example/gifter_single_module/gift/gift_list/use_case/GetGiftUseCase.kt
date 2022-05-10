package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.gift_list.repository.GiftRepository
import com.example.gifter_single_module.gift.gift_detail.model.Gift

class GetGiftUseCase(
    private val repository: GiftRepository
) {
    suspend operator fun invoke(id: Int): Gift? {
        return repository.getGift(id)
    }
}
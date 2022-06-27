package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_detail.model.InvalidGiftException
import com.example.gifter_single_module.gift.repository.GiftRepository

class AddEditGiftUseCase(
    private val repository: GiftRepository
) {
    @Throws(InvalidGiftException::class)
    suspend operator fun invoke(gift: Gift) {
        when {
            gift.title.isBlank() -> {
                throw InvalidGiftException("Gifts title cannot be blank.")
            }
            gift.ownerName.isBlank() -> {
                throw InvalidGiftException("Gifts owners name cannot be blank.")
            }
            gift.description.isBlank() -> {
                throw InvalidGiftException("Gifts description cannot be blank.")
            }
            else -> repository.addEditGift(gift)
        }
    }
}
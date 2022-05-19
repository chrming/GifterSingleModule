package com.example.gifter_single_module.gift.gift_detail.use_case

//import android.util.Log
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_detail.model.InvalidGiftException
import com.example.gifter_single_module.gift.repository.GiftRepository

class AddEditGiftUseCase(
    private val repository: GiftRepository
) {
    @Throws(InvalidGiftException::class)
    suspend operator fun invoke(gift: Gift) {
        if (gift.title.isBlank()) {
            throw InvalidGiftException("Gifts title cannot be blank.")
        }
        if (gift.ownerName.isBlank()) {
            throw InvalidGiftException("Gifts owners name cannot be blank.")
        }
        if (gift.description.isBlank()) {
            throw InvalidGiftException("Gifts description cannot be blank.")
        }
        if (gift.price.toString().isBlank() || gift.price.toString().isEmpty()) {
            throw InvalidGiftException("There are no free gifts...")
        }
        repository.addEditGift(gift)
    }
}
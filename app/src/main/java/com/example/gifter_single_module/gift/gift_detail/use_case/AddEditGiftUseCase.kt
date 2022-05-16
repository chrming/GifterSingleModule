package com.example.gifter_single_module.gift.gift_detail.use_case

//import android.util.Log
import com.example.gifter_single_module.gift.repository.GiftRepository
import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_detail.model.InvalidGiftException

class AddEditGiftUseCase(
    private val repository: GiftRepository
) {
    @Throws(InvalidGiftException::class)
    suspend operator fun invoke(gift: Gift) {

        if (gift.title.isBlank()) {
//            Log.d("CHM", "title blank: ${gift.title}")
            throw InvalidGiftException("Gifts title cannot be blank.")
        }
        if (gift.ownerName.isBlank()) {
//            Log.d("CHM", "ownerName blank: ${gift.ownerName}")

            throw InvalidGiftException("Gifts owners name cannot be blank.")
        }
        if (gift.description.isBlank()) {
//            Log.d("CHM", "description blank: ${gift.description}")

            throw InvalidGiftException("Gifts description cannot be blank.")
        }
        if (gift.price.toString().isBlank()) {
//            Log.d("CHM", "price blank: ${gift.price}")

            throw InvalidGiftException("There are no free gifts...")
        }
        repository.addEditGift(gift)
    }
}
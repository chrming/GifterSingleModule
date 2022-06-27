package com.example.gifter_single_module.profile.profile_detail.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_detail.use_case.ProfileDetailUseCaseWrapper
import com.example.gifter_single_module.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val profileDetailUseCase: ProfileDetailUseCaseWrapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var profile: Profile = Profile(-1, "", Long.MAX_VALUE, null)
    private val _profileGiftListState = mutableStateOf(ProfileGiftListState())
    val profileGiftListState = _profileGiftListState

    private var getGiftsJob: Job? = null

    init {
        savedStateHandle.get<Int>("profileId")?.let { profileId ->
            viewModelScope.launch {
                profile = profileDetailUseCase.getProfile(profileId)!!
                getGifts(GiftsOrder.Title(OrderType.Descending))
            }
        }
    }

    private fun getGifts(giftsOrder: GiftsOrder) {
        getGiftsJob?.cancel()
        getGiftsJob = profile.profileId?.let { profileId ->
            profileDetailUseCase.getWithGifts(profileId)
                .onEach { gifts ->
                    _profileGiftListState.value = profileGiftListState.value.copy(
                        profileGiftList = gifts
                    )
                    Log.d("chm", "profileId: $profileId")
                }
                .launchIn(viewModelScope)
        }
    }
}
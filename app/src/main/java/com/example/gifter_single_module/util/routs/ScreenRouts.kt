package com.example.gifter_single_module.util.routs

sealed class Screen(val route: String){
    object GiftDetailScreen: Screen("gift_detail_screen")
    object GiftListScreen: Screen("gift_list_screen")
    object AddEditGiftScreen: Screen("add_edit_gift_screen")
}
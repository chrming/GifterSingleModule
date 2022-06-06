package com.example.gifter_single_module.profile.profile_list

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.profile.profile_detail.presentation.ProfileDetailViewModel

@Composable
fun ProfileListScreen(
    viewModel: ProfileDetailViewModel = hiltViewModel(),
    onClickNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
}
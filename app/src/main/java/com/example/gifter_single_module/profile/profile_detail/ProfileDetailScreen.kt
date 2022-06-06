package com.example.gifter_single_module.profile.profile_detail

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gifter_single_module.profile.profile_detail.presentation.ProfileDetailViewModel

@Composable
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
}
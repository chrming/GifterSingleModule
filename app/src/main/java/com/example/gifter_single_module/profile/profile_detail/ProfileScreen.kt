package com.example.gifter_single_module.profile.profile_detail

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gifter_single_module.profile.profile_detail.view_model.ProfilesViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfilesViewModel = hiltViewModel()
) {
    val state = viewModel.profilesState.value
    val scaffoldState = rememberScaffoldState()
}
package com.example.gifter_single_module.profile.profile_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.profile.profile_list.components.OrderSection
import com.example.gifter_single_module.profile.profile_list.components.ProfileItem
import com.example.gifter_single_module.profile.profile_list.presentation.ProfileListEvent
import com.example.gifter_single_module.profile.profile_list.presentation.ProfileListViewModel
import com.example.gifter_single_module.profile.profile_list.presentation.UiEvent
import com.example.gifter_single_module.util.routs.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProfileListScreen(
    viewModel: ProfileListViewModel = hiltViewModel(),
    onClickNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.profileListState.value
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(message = it.message, actionLabel = "Undo")
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(ProfileListEvent.RestoreProfile)
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickNavigate(Screen.AddEditProfileScreen.route) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add profile")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Profiles", style = MaterialTheme.typography.h4)
                IconButton(
                    onClick = {
                        viewModel.onEvent(ProfileListEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Sort"
                    )
                }
                AnimatedVisibility(visible = state.isOrderSectionVisible) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        profileOrder = state.profileOrder,
                        onOrderChange = { order ->
                            viewModel.onEvent(ProfileListEvent.Order(order))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.profileList) { profile ->
                    ProfileItem(
                        profile = profile,
                        onClick = {
                            onClickNavigate(Screen.ProfileDetailScreen.route + "?profileId=${profile.profileId}")
                        },
                        onDeleteClick = { viewModel.onEvent(ProfileListEvent.DeleteProfile(profile)) }
                    )
                }
            }
        }
    }
}
package com.example.gifter_single_module.profile.profile_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifter_single_module.gift.gift_list.components.GiftItem
import com.example.gifter_single_module.profile.profile_detail.presentation.ProfileDetailViewModel
import com.example.gifter_single_module.profile.profile_detail.util.DateTransformation
import com.example.gifter_single_module.profile.profile_detail.util.DayMonthTransformation
import com.example.gifter_single_module.util.routs.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel = hiltViewModel(),
    onClickNavigate: (String) -> Unit
) {
    val profileId = viewModel.profile.profileId
    val profileName = viewModel.profile.name
    val profileBirthdayDate = viewModel.profile.birthdayDate
    val profileNamedayDate = viewModel.profile.namedayDate

    val giftList = viewModel.profileGiftListState.value.profileGiftList

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onClickNavigate(Screen.AddEditProfileScreen.route + "?profileId=${profileId}")
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit profile")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                        OutlinedTextField(
                            value = profileName,
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Birthday Date") },
                            singleLine = true,
                            readOnly = true,

                            )

                        OutlinedTextField(
                            value = profileBirthdayDate.toString(),
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Birthday Date") },
                            singleLine = true,
                            readOnly = true,
                            visualTransformation = DateTransformation()
                        )

                        OutlinedTextField(
                            value = profileNamedayDate?.toString() ?: "",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Nameday Date") },
                            singleLine = true,
                            readOnly = true,
                            visualTransformation = DayMonthTransformation()
                        )
                    }
                }
            }
            items(giftList) { gift ->
                GiftItem(gift = gift, onClick = {
                    onClickNavigate(Screen.AddEditGiftScreen.route + "?giftId=${gift.giftId}")
                }, onDeleteClick = {})
            }
        }
    }
}
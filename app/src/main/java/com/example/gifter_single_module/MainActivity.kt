package com.example.gifter_single_module
//TODO clearing the backstack
//TODO relation between AddEditGiftScreen profileName and profiles
//TODO viewModel for GiftDetail
//TODO GiftDetailScreen as a middleman between list and addEdit
//TODO proper date parsing
//TODO caching images with coil library
//TODO adding profile image
//TODO maybe choosing date of birth instead typing it
//TODO if no profiles "to create gift first create a profile" or let user create gift without owner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gifter_single_module.components.bottom_navigation.BottomNavigationBar
import com.example.gifter_single_module.components.bottom_navigation.BottomNavigationItem
import com.example.gifter_single_module.gift.gift_detail.GiftDetailScreen
import com.example.gifter_single_module.gift.gift_list.GiftListScreen
import com.example.gifter_single_module.profile.profile_detail.AddEditProfileScreen
import com.example.gifter_single_module.profile.profile_detail.ProfileDetailScreen
import com.example.gifter_single_module.profile.profile_list.ProfileListScreen
import com.example.gifter_single_module.ui.theme.Gifter_single_moduleTheme
import com.example.gifter_single_module.util.routs.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gifter_single_moduleTheme {
                // A surface container using the 'background' color from the theme
                Gifter()
            }
        }
    }
}

@Composable
fun Gifter() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController,
            items = listOf(
                BottomNavigationItem(
                    name = "Profiles",
                    route = Screen.ProfileListScreen.route,
                    icon = Icons.Default.People
                ),
                BottomNavigationItem(
                    name = "Gifts",
                    route = Screen.GiftListScreen.route,
                    icon = Icons.Default.Cake
                )
            )
        )
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(navController = navController, startDestination = Screen.GiftListScreen.route) {

                // Gifts composables
                // List Screen
                composable(Screen.GiftListScreen.route) {
                    GiftListScreen(onClickNavigate = { route ->
                        navController.navigate(route)
                    })
                }

                // AddEdit Screen
                composable(
                    route = Screen.AddEditGiftScreen.route + "?giftId={giftId}",
                    arguments = listOf(
                        navArgument(name = "giftId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    GiftDetailScreen(onLaunch = {
                        navController.navigateUp()
                    })
                }

                // Profile composables
                // List Screen
                composable(Screen.ProfileListScreen.route) {
                    ProfileListScreen(onClickNavigate = { route ->
                        navController.navigate(route)
                    })
                }

                // AddEdit Screen
                composable(
                    route = Screen.AddEditProfileScreen.route + "?profileId={profileId}",
                    arguments = listOf(
                        navArgument(name = "profileId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    AddEditProfileScreen(onLaunch = { route ->
                        navController.navigate(route)
                    })
                }

                // Detail Screen
                composable(Screen.ProfileDetailScreen.route + "?profileId={profileId}",
                    arguments = listOf(
                        navArgument(name = "profileId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    ProfileDetailScreen(onClickNavigate = { route ->
                        navController.navigate(route)
                    })
                }
            }
        }
    }
}
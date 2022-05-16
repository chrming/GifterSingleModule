package com.example.gifter_single_module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gifter_single_module.gift.gift_detail.GiftDetailScreen
import com.example.gifter_single_module.gift.gift_list.GiftListScreen
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    Gifter()
                }
            }
        }
    }
}

@Composable
fun Gifter() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.GiftListScreen.route) {
        composable(Screen.GiftListScreen.route) {
            GiftListScreen(onClickNavigate = { route ->
                navController.navigate(route)
            })
        }
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
    }
}
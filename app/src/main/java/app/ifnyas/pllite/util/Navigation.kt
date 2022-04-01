package app.ifnyas.pllite.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.ifnyas.pllite.model.Screen
import app.ifnyas.pllite.ui.view.CameraView
import app.ifnyas.pllite.ui.view.MainScreen
import app.ifnyas.pllite.ui.view.ResultScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Screen.MainScreen.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController, "")
        }

        composable(Screen.CameraScreen.route) { CameraView(navController) }

        composable(Screen.Exit.route) { (LocalContext.current as Activity).finishAffinity() }

        composable(
            "${Screen.MainScreen.route}/{nik}",
            arguments = listOf(navArgument(name = "nik") { type = NavType.StringType })
        ) {
            MainScreen(navController, it.arguments?.getString("nik") ?: "")
        }

        composable("${Screen.ResultScreen.route}/{nik}/{code}/{message}",
            arguments = listOf(
                navArgument(name = "nik") { type = NavType.StringType },
                navArgument(name = "code") { type = NavType.IntType },
                navArgument(name = "message") { type = NavType.StringType }
            )
        ) {
            ResultScreen(
                navController,
                it.arguments?.getString("nik") ?: "",
                it.arguments?.getInt("code") ?: 0,
                it.arguments?.getString("message") ?: ""
            )
        }
    }
}
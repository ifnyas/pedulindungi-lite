package app.ifnyas.pllite

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.ifnyas.pllite.ui.view.CameraView
import app.ifnyas.pllite.ui.view.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            "${Screen.MainScreen.route}/{nik}",
            arguments = listOf(navArgument(name = "nik") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) { MainScreen(navController, it.arguments?.getString("nik")) }
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Screen.CameraScreen.route) { CameraView(navController) }
    }
}
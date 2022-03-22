package app.ifnyas.pllite.ui.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.ifnyas.pllite.R
import app.ifnyas.pllite.Screen
import app.ifnyas.pllite.ui.theme.Gray50

@Composable
fun MainScreen(navController: NavController, nik: String? = "") {
    @Composable
    fun fabIcon() = Icon(
        painterResource(R.drawable.ic_baseline_camera_alt_24),
        "Camera",
        tint = Gray50
    )

    val showDialog = remember { mutableStateOf(false) }
    @Composable if (showDialog.value) {
        val title = "Hi, Devs!"
        val body =
            "Thank you for checking out this app.\nMade by @Ifnyas, just because not everyone has a working smartphone."
        AlertDialog(
            title = { Text(title) },
            text = { Text(body) },
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = { showDialog.value = false })
                { Text(text = "OK") }
            },
        )
    }

    fun navCamera() = navController.navigate(Screen.CameraScreen.route)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Peduli Lindungi - Lite") },
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {
                        Icon(
                            painterResource(R.drawable.ic_launcher_foreground),
                            "Info", tint = Gray50
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navCamera() },
                content = { fabIcon() })
        }, content = { ComposeWebView(nik) }
    )
}
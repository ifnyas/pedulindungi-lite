package app.ifnyas.pllite.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.ifnyas.pllite.model.Screen
import app.ifnyas.pllite.ui.theme.Gray500
import app.ifnyas.pllite.ui.theme.Green500
import app.ifnyas.pllite.ui.theme.Red500

@Composable
fun ResultScreen(navController: NavController, nik: String, code: Int, message: String) {
    val nikSpaced = nik.trim()
        .replace("\"", "")
        .replace("....".toRegex(), "$0 ")

    fun navExit() = navController.navigate(Screen.Exit.route)
    fun navCamera() = navController.navigate(Screen.CameraScreen.route)

    @Composable
    fun statusIcon() = Icon(
        if (code == 200) Icons.Default.CheckCircle else Icons.Default.Clear,
        tint = if (code == 200) Green500 else Red500,
        contentDescription = if (code == 200) "Success icon" else "Failed Icon",
        modifier = Modifier
            .width(160.dp)
            .aspectRatio(1f),
    )

    Scaffold(
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { navExit() },
                    content = { Text(text = "CLOSE APP") })
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { navCamera() },
                    content = { Text(text = "SCAN AGAIN") })
            }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.66f)
            ) {
                statusIcon()
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = nikSpaced, style = MaterialTheme.typography.body1,
                    color = Gray500
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message, style = MaterialTheme.typography.h5)
            }
        }
    )
}
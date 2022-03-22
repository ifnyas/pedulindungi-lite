package app.ifnyas.pllite

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import app.ifnyas.pllite.ui.theme.PLLiteTheme
import com.markodevcic.peko.Peko
import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainScope().launch {
            val permit = Peko.requestPermissionsAsync(
                this@MainActivity,
                Manifest.permission.CAMERA,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (permit is PermissionResult.Denied) finishAfterTransition()
            else setContent {
                PLLiteTheme {
                    window?.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                    Surface(color = MaterialTheme.colors.background) {
                        Navigation()
                    }
                }
            }
        }
    }
}
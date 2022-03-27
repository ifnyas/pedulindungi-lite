package app.ifnyas.pllite.ui.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import app.ifnyas.pllite.model.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.json.JSONObject


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ComposeWebView(navController: NavController, nik: String) {
    AndroidView(factory = {
        WebView(it).apply {
            // set cookie manager
            val cookies = CookieManager.getInstance()
            cookies.setAcceptCookie(true)
            cookies.setAcceptThirdPartyCookies(this@apply, true)

            // set web view client
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    evaluateJavascript(
                        context.assets.open("interceptor.js").reader().readText(),
                        null
                    )
                }

                override fun onPageFinished(view: WebView, url: String) {
                    CoroutineScope(Main).launch {
                        val funNik = "document.querySelector('[type=\"text\"]').value = '$nik'"
                        evaluateJavascript(
                            "(function() { $funNik; }) ();",
                            null
                        )
                    }
                }
            }.apply {
                settings.apply {
                    javaScriptEnabled = true
                }
            }

            // set web chrome client
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(msg: ConsoleMessage): Boolean {
                    // Log.d("TAG", "onConsoleMessage: ${msg.message()}")
                    try {
                        val json = JSONObject(msg.message())
                        val vaccine = json.getJSONObject("hasilVaksin")
                        val code = vaccine.getInt("code")
                        val message = vaccine.getString("message")

                        val funNik = "return document.querySelector('[type=\"text\"]').value;"
                        evaluateJavascript("(function() { $funNik }) ();") { value ->
                            navController.navigate(
                                Screen.ResultScreen.withArgs(
                                    args = arrayOf(value, "$code", message)
                                )
                            )
                        }
                    } catch (e: Exception) {
                        // Log.e("TAG", "onConsoleMessage: ${e.message}")
                    }
                    return true
                }
            }

            // load
            clearCache(true)
            loadUrl("https://cekmandiri.pedulilindungi.id")
        }
    })
}

/* Success res
{
  "hasilTest": {
    "success": true,
    "code": 200,
    "data": {
      "tanggalPcr": null,
      "adaHasilPcr": false,
      "tanggalAntigen": null,
      "adaHasilAntigen": false
    },
    "message": "Scan nik success"
  },
  "hasilVaksin": {
    "success": true,
    "code": 200,
    "message": "Data vaksin ada",
    "data": {
      "adaDataVaksin": true
    }
  }
}
 */

/* Failed res
{
  "hasilTest": {
    "success": true,
    "code": 200,
    "data": {
      "tanggalPcr": null,
      "adaHasilPcr": false,
      "tanggalAntigen": null,
      "adaHasilAntigen": false
    },
    "message": "Scan nik success"
  },
  "hasilVaksin": {
    "code": 404,
    "success": false,
    "message": "Data vaksin tidak ada",
    "data": {
      "adaDataVaksin": false
    }
  },
}
 */

/* init click event
val downTime = SystemClock.uptimeMillis()
val eventTime = downTime + 100
val x = 100.0f
val y = 1000.0f
val metaState = 0

val downEvent = MotionEvent.obtain(
    downTime,
    eventTime,
    MotionEvent.ACTION_DOWN,
    x,
    y,
    metaState
)

val upEvent = MotionEvent.obtain(
    downTime,
    eventTime,
    MotionEvent.ACTION_UP,
    x,
    y,
    metaState
)
 */

// Handler Touch Event
/*
Handler(Looper.getMainLooper()).postDelayed({
   dispatchTouchEvent(downEvent)
   dispatchTouchEvent(upEvent)
},1000)

val fun2 ="document.querySelector('[type=\"submit\"]').click()"
Handler(Looper.getMainLooper()).postDelayed({
   loadUrl("javascript: (function() { $fun2; }) ();")
},2500)
*/

// PL Entry
/*
     setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                Toast.makeText(context, "UP: ${event.x}, ${event.y}", Toast.LENGTH_SHORT).show()
            }
        }
        performClick()
    }

    val userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
        (KHTML, like Gecko) Chrome/96.14.887.864 Safari/537.36"

    domStorageEnabled = true
    databaseEnabled = true
    javaScriptCanOpenWindowsAutomatically = true
    setGeolocationEnabled(true)
    setSupportMultipleWindows(true)
    userAgentString = userAgent

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ){
        super.onGeolocationPermissionsShowPrompt(origin, callback)
        callback?.invoke(origin, true, true)
    }

    loadUrl("https://www.tokopedia.com/peduli-lindungi/entry")
 */
package app.ifnyas.pllite.ui.view

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ComposeWebView(nik: String?) {
    AndroidView(factory = {
        WebView(it).apply {
            // set cookie manager
            val cookies = CookieManager.getInstance()
            cookies.setAcceptCookie(true)
            cookies.setAcceptThirdPartyCookies(this@apply, true)

            // set web view client
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val fun1 = "document.querySelector('[type=\"text\"]').value = '$nik'"
                        loadUrl("javascript: (function() { $fun1; }) ();")
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
                    Log.d("WebView", msg.message())
                    return true
                }
            }

            // load
            clearCache(true)
            loadUrl("https://cekmandiri.pedulilindungi.id")
        }
    })
}

// init click event
/*
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
package app.ifnyas.pllite.model

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object CameraScreen : Screen("camera_screen")
    object ResultScreen : Screen("result_screen")
    object Exit : Screen("exit")

    fun withArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}

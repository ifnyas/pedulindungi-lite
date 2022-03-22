package app.ifnyas.pllite

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object CameraScreen : Screen("camera_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

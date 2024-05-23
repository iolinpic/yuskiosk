import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import composables.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true, undecorated = true,
        state = WindowState(
            size = DpSize(1920.dp, 1080.dp),
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        MaterialTheme {
            App()
        }
    }
}

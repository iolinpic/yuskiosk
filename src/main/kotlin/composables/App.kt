package composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.qrose.rememberQrCodePainter

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var windowTitle by remember { mutableStateOf("Информация") }
    var windowLogo by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitleBarDisplay(windowTitle, windowLogo)
        Button(onClick = {
            text = "Hello, Desktop!"
            windowTitle = "Обновленная информация"
            windowLogo = false
        }) {
            Text(text)
        }
        Image(
            painter = rememberQrCodePainter("https://example.com"),
            contentDescription = "QR code referring to the example.com website"
        )
    }


}
package composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import io.github.alexzhirkevich.qrose.rememberQrCodePainter

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }


    Button(onClick = {
        text = "Hello, Desktop!"
    }) {
        Text(text)
    }
    Image(
        painter = rememberQrCodePainter("https://example.com"),
        contentDescription = "QR code referring to the example.com website"
    )

}
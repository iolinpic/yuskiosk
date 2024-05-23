package composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds


@Composable
@Preview
fun TitleBarDisplay(title: String, hideLogo: Boolean = false) {
    val clockWatch = remember { ClockWatch() }

    DisposableEffect(clockWatch) {
        clockWatch.start()
        onDispose {
            clockWatch.stop()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray).height(100.dp).padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!hideLogo) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = rememberQrCodePainter("https://example.com"),
                    contentDescription = "QR code referring to the example.com website"
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(title, style = MaterialTheme.typography.h3)
        }
        Text(text = clockWatch.formatedTime, style = MaterialTheme.typography.h3)
    }
}

class ClockWatch {
    var formatedTime by mutableStateOf("00.00 00:00")

    private var coroutineScope = CoroutineScope(Dispatchers.Default)
    private var isActive = false

    fun start() {
        if (isActive) return
        coroutineScope.launch {
            this@ClockWatch.isActive = true
            while (isActive) {
                val x = LocalDateTime.now()
                val todayFormatter = DateTimeFormatter.ofPattern("dd.MM HH:mm")
                formatedTime = x.format(todayFormatter)
                delay(5.seconds)
            }
        }
    }

    fun stop() {
        isActive = false
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
    }

}

import android.app.Application
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

fun main() = application {

    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {

        val storage = mutableMapOf<String, String>()
        override fun clear(key: String) {
            storage.remove(key)
        }

        override fun log(msg: String) = println(msg)

        override fun retrieve(key: String) = storage[key]

        override fun store(key: String, value: String) = storage.set(key, value)

    })

    val options = FirebaseOptions(
        projectId = "REPLACE WITH YOUR FILE projectId",
        applicationId = "REPLACE WITH YOUR FILE applicationId",
        apiKey = "REPLACE WITH YOUR FILE apiKey"
    )

    Firebase.initialize(Application(), options)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Firestore",
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 1280.dp,
            height = 720.dp
        )
    ) {
        App()
    }
}
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import data.InMemoryUserRepository
import presentation.UserScreen

@Composable
fun App() {
    val userRepository = remember { InMemoryUserRepository() }
    UserScreen(userRepository)
}
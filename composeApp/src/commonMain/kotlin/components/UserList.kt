package components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.User

@Composable
fun UserList(users: List<User>, modifier: Modifier = Modifier, userClicked: (User) -> Unit) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users, key = { it.id }) { user ->
            UserItem(user = user) {
                userClicked(user)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
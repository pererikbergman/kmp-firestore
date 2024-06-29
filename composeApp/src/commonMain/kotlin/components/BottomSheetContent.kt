@file:OptIn(ExperimentalMaterial3Api::class)

package components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.User

@Composable
fun BottomSheetContent(
    user: User? = null,
    onSave: (User) -> Unit,
    onDelete: (User?) -> Unit
) {
    var name by remember { mutableStateOf(user?.name ?: "") }
    var title by remember { mutableStateOf(user?.title ?: "") }
    var company by remember { mutableStateOf(user?.company ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            singleLine = true,
            label = { Text("Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = company,
            onValueChange = { company = it },
            singleLine = true,
            label = { Text("Company") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onSave(User(user?.id ?: "", name, title, company)) }) {
                Text(text = if (user == null) "Save" else "Update")
            }
            Button(onClick = { onDelete(user) }) {
                Text("Delete")
            }
        }
    }
}
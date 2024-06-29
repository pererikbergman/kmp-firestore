package data

import domain.User
import domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class InMemoryUserRepository : UserRepository {

    private val users = MutableStateFlow<List<User>>(emptyList())
    private var id = 100

    init {
        users.value = List(15) {
            User(
                id = "ID: $it",
                name = "User $it",
                title = "Title $it",
                company = "Company $it"
            )
        }
    }

    override fun getUsers(): Flow<List<User>> {
        return users
    }

    override fun getUserById(id: String): Flow<User?> {
        return users.map { userList -> userList.find { it.id == id } }
    }

    override suspend fun addUser(user: User) {
        users.value += user.copy(id = (id++).toString())
    }

    override suspend fun updateUser(user: User) {
        users.value = users.value.map {
            if (it.id == user.id) user else it
        }
    }

    override suspend fun deleteUser(user: User) {
        users.value = users.value.filter { it.id != user.id }
    }
}
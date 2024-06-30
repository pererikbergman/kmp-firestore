package data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import domain.User
import domain.UserRepository
import kotlinx.coroutines.flow.flow

class FirestoreUserRepository : UserRepository {

    private val firestore = Firebase.firestore

    override fun getUsers() = flow {
        firestore.collection("USERS").snapshots.collect { querySnapshot ->
            val users = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<User>()
            }
            emit(users)
        }
    }

    override fun getUserById(id: String) = flow {
        firestore.collection("USERS").document(id).snapshots.collect { documentSnapshot ->
            emit(documentSnapshot.data<User>())
        }
    }

    override suspend fun addUser(user: User) {
        val userId = generateRandomStringId()
        firestore.collection("USERS")
            .document(userId)
            .set(user.copy(id = userId))
    }

    override suspend fun updateUser(user: User) {
        firestore.collection("USERS").document(user.id).set(user)
    }

    override suspend fun deleteUser(user: User) {
        firestore.collection("USERS").document(user.id).delete()
    }

    private fun generateRandomStringId(length: Int = 20): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}
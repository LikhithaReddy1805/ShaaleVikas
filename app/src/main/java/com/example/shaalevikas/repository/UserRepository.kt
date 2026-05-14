package com.example.shaalevikas.repository

import com.example.shaalevikas.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    fun saveUserRole(
        role: String
    ) {

        val currentUser = auth.currentUser ?: return

        val user = User(

            uid = currentUser.uid,

            email = currentUser.email ?: "",

            role = role
        )

        db.collection("users")
            .document(currentUser.uid)
            .set(user)
    }

    fun getUserRole(
        onResult: (String) -> Unit
    ) {

        val currentUser = auth.currentUser

        if (currentUser == null) {

            onResult("")
            return
        }

        db.collection("users")
            .document(currentUser.uid)
            .get()
            .addOnSuccessListener {

                val role =
                    it.getString("role") ?: ""

                onResult(role)
            }
    }
}
package com.example.shaalevikas.repository

import com.example.shaalevikas.model.Need
import com.google.firebase.firestore.FirebaseFirestore

class NeedRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addNeed(
        need: Need,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val document = db.collection("needs").document()

        val newNeed = need.copy(
            id = document.id
        )

        document.set(newNeed)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getNeeds(
        onResult: (List<Need>) -> Unit
    ) {

        db.collection("needs")
            .addSnapshotListener { value, _ ->

                val needs = value?.documents?.mapNotNull {

                    it.toObject(Need::class.java)

                } ?: emptyList()

                onResult(needs)
            }
    }

    fun pledgeSupport(
        need: Need
    ) {

        val updatedAmount =
            need.collectedAmount + 1000

        db.collection("needs")
            .document(need.id)
            .update(
                "collectedAmount",
                updatedAmount
            )
    }

    fun deleteNeed(
        needId: String
    ) {

        db.collection("needs")
            .document(needId)
            .delete()
    }

    fun updateNeed(
        need: Need
    ) {

        db.collection("needs")
            .document(need.id)
            .set(need)
    }
}
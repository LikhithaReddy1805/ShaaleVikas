package com.example.shaalevikas.repository

import com.example.shaalevikas.model.Contribution
import com.example.shaalevikas.model.Need
import com.google.firebase.firestore.FirebaseFirestore

class ContributionRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addContribution(

        contribution: Contribution,

        need: Need

    ) {

        val contributionDoc =
            db.collection("contributions")
                .document()

        val newContribution =
            contribution.copy(
                id = contributionDoc.id
            )

        contributionDoc.set(newContribution)

        val updatedAmount =
            need.collectedAmount +
                    contribution.amount

        db.collection("needs")
            .document(need.id)
            .update(
                "collectedAmount",
                updatedAmount
            )
    }

    fun getContributions(

        onResult: (List<Contribution>) -> Unit

    ) {

        db.collection("contributions")
            .addSnapshotListener { value, _ ->

                val contributions =
                    value?.documents?.mapNotNull {

                        it.toObject(
                            Contribution::class.java
                        )
                    } ?: emptyList()

                onResult(contributions)
            }
    }
}
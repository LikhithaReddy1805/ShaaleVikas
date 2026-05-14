package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.shaalevikas.model.Contribution
import com.example.shaalevikas.model.Need
import com.example.shaalevikas.repository.ContributionRepository
import com.example.shaalevikas.viewmodel.NeedViewModel

@Composable
fun HomeScreen(
    viewModel: NeedViewModel = viewModel()
) {

    val needs = viewModel.needs

    var searchQuery by remember {
        mutableStateOf("")
    }

    val filteredNeeds = needs.filter {

        it.title.contains(searchQuery, true) ||
                it.description.contains(searchQuery, true)
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(horizontal = 12.dp),

        contentPadding = PaddingValues(
            top = 12.dp,
            bottom = 120.dp
        )
    ) {

        item {

            OutlinedTextField(
                value = searchQuery,

                onValueChange = {

                    searchQuery = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {

                    Text("Search school needs...")
                },

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },

                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "School Needs",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredNeeds) { need ->

            NeedCard(need)
        }
    }
}

@Composable
fun NeedCard(
    need: Need
) {

    val repository = ContributionRepository()

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showSuccessDialog by remember {
        mutableStateOf(false)
    }

    var contributorName by remember {
        mutableStateOf("")
    }

    var contributionAmount by remember {
        mutableStateOf("")
    }

    val progress =
        if (need.targetAmount > 0)
            need.collectedAmount.toFloat() /
                    need.targetAmount.toFloat()
        else
            0f

    // Contribution Dialog
    if (showDialog) {

        AlertDialog(

            onDismissRequest = {

                showDialog = false
            },

            title = {

                Text("Support This Need")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = contributorName,

                        onValueChange = {

                            contributorName = it
                        },

                        label = {

                            Text("Your Name")
                        },

                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedTextField(
                        value = contributionAmount,

                        onValueChange = {

                            contributionAmount =
                                it.filter { char ->
                                    char.isDigit()
                                }
                        },

                        label = {

                            Text("Contribution Amount")
                        },

                        placeholder = {

                            Text("Enter amount")
                        },

                        singleLine = true
                    )
                }
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        if (
                            contributorName.isNotEmpty() &&
                            contributionAmount.isNotEmpty()
                        ) {

                            val contribution =
                                Contribution(

                                    needId = need.id,

                                    needTitle = need.title,

                                    contributorName =
                                        contributorName,

                                    amount =
                                        contributionAmount.toIntOrNull() ?: 0
                                )

                            repository.addContribution(

                                contribution = contribution,

                                need = need
                            )

                            showDialog = false

                            showSuccessDialog = true

                            contributorName = ""

                            contributionAmount = ""
                        }
                    }
                ) {

                    Text("Confirm")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {

                        showDialog = false
                    }
                ) {

                    Text("Cancel")
                }
            }
        )
    }

    // Payment Success Dialog
    if (showSuccessDialog) {

        AlertDialog(

            onDismissRequest = {

                showSuccessDialog = false
            },

            title = {

                Text(
                    text = "Payment Successful ✓"
                )
            },

            text = {

                Text(
                    text =
                        "Thank you for supporting rural education."
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        showSuccessDialog = false
                    }
                ) {

                    Text("OK")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),

        shape = RoundedCornerShape(24.dp),

        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column {

            AsyncImage(
                model =
                    if (need.imageUrl.isNotEmpty())
                        need.imageUrl
                    else
                        "https://images.unsplash.com/photo-1580582932707-520aed937b7b",

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = need.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = need.description,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(

                    progress = { progress },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(
                            RoundedCornerShape(50)
                        ),

                    color = Color(0xFF14B8A6)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =
                        "₹${need.collectedAmount} raised of ₹${need.targetAmount}",

                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = {

                        showDialog = true
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),

                    shape = RoundedCornerShape(14.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFF4F46E5)
                    )
                ) {

                    Text(
                        text = "Pledge Support"
                    )
                }
            }
        }
    }
}
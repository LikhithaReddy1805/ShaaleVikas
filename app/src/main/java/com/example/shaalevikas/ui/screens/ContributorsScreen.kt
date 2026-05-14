package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shaalevikas.model.Contribution
import com.example.shaalevikas.repository.ContributionRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributorsScreen() {

    val repository = remember {
        ContributionRepository()
    }

    var contributions by remember {
        mutableStateOf<List<Contribution>>(
            emptyList()
        )
    }

    LaunchedEffect(Unit) {

        repository.getContributions {

            contributions = it
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopAppBar(
            title = {

                Text(
                    text = "Contributors",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4F46E5)
            )
        )

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            items(contributions) { contribution ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),

                    shape = RoundedCornerShape(24.dp),

                    elevation =
                        CardDefaults.cardElevation(8.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Column {

                            Text(
                                text =
                                    contribution.contributorName,
                                fontWeight =
                                    FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(6.dp)
                            )

                            Text(
                                text =
                                    contribution.needTitle,
                                color = Color.Gray
                            )
                        }

                        Column(
                            horizontalAlignment =
                                Alignment.End
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.Groups,
                                contentDescription = null,
                                tint = Color(0xFF14B8A6)
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(
                                text =
                                    "₹${contribution.amount}",
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    Color(0xFF4F46E5)
                            )
                        }
                    }
                }
            }
        }
    }
}
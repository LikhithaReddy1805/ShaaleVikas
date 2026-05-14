package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shaalevikas.model.Contribution
import com.example.shaalevikas.repository.ContributionRepository

@Composable
fun HallOfFameScreen() {

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

            contributions =
                it.sortedByDescending { contribution ->
                    contribution.amount
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp)
    ) {

        Text(
            text = "Hall of Fame",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Recognizing alumni who support education.",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {

            itemsIndexed(contributions) { index, contribution ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),

                    shape = RoundedCornerShape(26.dp),

                    elevation = CardDefaults.cardElevation(8.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        Color.White,
                                        Color(0xFFFFFBEB)
                                    )
                                )
                            )
                            .fillMaxWidth()
                            .padding(20.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(CircleShape)
                                .background(
                                    Color(0xFFF59E0B)
                                ),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(
                                text =
                                    "#${index + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text =
                                    contribution.contributorName,
                                fontWeight = FontWeight.Bold,
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
                                    Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = Color(0xFFF59E0B)
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
                                    Color(0xFF4F46E5),

                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
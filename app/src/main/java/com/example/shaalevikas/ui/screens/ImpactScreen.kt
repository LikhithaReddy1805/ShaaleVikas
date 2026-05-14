package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ImpactStory(

    val title: String,

    val description: String,

    val imageUrl: String
)

@Composable
fun ImpactScreen() {

    val stories = listOf(

        ImpactStory(

            title = "Library Renovation",

            description =
                "Alumni contributions helped renovate the school library with new shelves and books.",

            imageUrl =
                "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f"
        ),

        ImpactStory(

            title = "Classroom Painting",

            description =
                "School walls were repaired and repainted to create a better learning environment.",

            imageUrl =
                "https://images.unsplash.com/photo-1509062522246-3755977927d7"
        ),

        ImpactStory(

            title = "Clean Drinking Water",

            description =
                "A new water purifier system was installed for students and staff.",

            imageUrl =
                "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp)
    ) {

        Text(
            text = "Impact Stories",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "See how alumni support transforms schools.",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {

            items(stories) { story ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),

                    shape = RoundedCornerShape(28.dp),

                    elevation = CardDefaults.cardElevation(8.dp)
                ) {

                    Column {

                        AsyncImage(
                            model = story.imageUrl,
                            contentDescription = null,

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),

                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.White,
                                            Color(0xFFF9FAFB)
                                        )
                                    )
                                )
                                .padding(18.dp)
                        ) {

                            Text(
                                text = story.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Text(
                                text = story.description,
                                color = Color.Gray,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
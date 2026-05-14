package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import com.example.shaalevikas.ui.theme.ThemeManager
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(

    role: String = "user",

    onLogout: () -> Unit

) {

    val darkMode by ThemeManager.isDarkMode

    val backgroundColor =
        if (darkMode)
            Color(0xFF121212)
        else
            Color(0xFFF8FAFC)

    val cardColor =
        if (darkMode)
            Color(0xFF1E1E1E)
        else
            Color.White

    val textColor =
        if (darkMode)
            Color.White
        else
            Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF4F46E5),
                            Color(0xFF14B8A6)
                        )
                    )
                ),

            contentAlignment =
                Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text =
                if (role == "admin")
                    "Headmaster"
                else
                    "Alumni Supporter",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold,

            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                if (role == "admin")
                    "Managing school development"
                else
                    "Supporting rural education",

            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Account Card
        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = cardColor
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Settings,

                        contentDescription = null,

                        tint = Color(0xFF4F46E5)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(10.dp)
                    )

                    Text(
                        text = "Account Settings",

                        fontWeight =
                            FontWeight.Bold,

                        color = textColor
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Dark Mode",
                        color = textColor
                    )

                    Switch(
                        checked = darkMode,

                        onCheckedChange = {

                            ThemeManager
                                .isDarkMode
                                .value = it
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = cardColor
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Activity",

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp,

                    color = textColor
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text =
                        if (role == "admin")
                            "Managing active school campaigns"
                        else
                            "Helping improve school infrastructure",

                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                FirebaseAuth
                    .getInstance()
                    .signOut()

                onLogout()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {

            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Logout"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
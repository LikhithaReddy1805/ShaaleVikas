package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AdminMainScreen(

    onLogout: () -> Unit

) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Add Need")
                    }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Manage")
                    }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Groups,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Contributors")
                    }
                )

                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = {
                        selectedIndex = 3
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            when (selectedIndex) {

                0 -> {

                    AddNeedScreen()
                }

                1 -> {

                    ManageNeedsScreen()
                }

                2 -> {

                    ContributorsScreen()
                }

                3 -> {

                    ProfileScreen(
                        onLogout = onLogout
                    )
                }
            }
        }
    }
}
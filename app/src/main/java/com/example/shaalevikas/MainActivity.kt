package com.example.shaalevikas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.example.shaalevikas.ui.screens.AdminMainScreen
import com.example.shaalevikas.ui.screens.AlumniMainScreen
import com.example.shaalevikas.ui.screens.LoginScreen
import com.example.shaalevikas.ui.screens.RoleSelectionScreen
import com.example.shaalevikas.ui.screens.SignupScreen
import com.example.shaalevikas.ui.screens.SplashScreen
import com.example.shaalevikas.ui.theme.ThemeManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val darkMode by ThemeManager.isDarkMode

            var showSplash by remember {
                mutableStateOf(true)
            }

            var currentScreen by remember {
                mutableStateOf("role")
            }

            var selectedRole by remember {
                mutableStateOf("")
            }

            MaterialTheme {

                Surface {

                    when {

                        showSplash -> {

                            SplashScreen(
                                onNavigate = {

                                    showSplash = false
                                }
                            )
                        }

                        currentScreen == "role" -> {

                            RoleSelectionScreen(

                                onAdminClick = {

                                    selectedRole = "admin"
                                    currentScreen = "login"
                                },

                                onUserClick = {

                                    selectedRole = "user"
                                    currentScreen = "login"
                                }
                            )
                        }

                        currentScreen == "signup" -> {

                            SignupScreen(

                                onSignupSuccess = {

                                    currentScreen = "login"
                                }
                            )
                        }

                        selectedRole == "admin" &&
                                currentScreen == "admin_dashboard" -> {

                            AdminMainScreen(

                                onLogout = {

                                    selectedRole = ""
                                    currentScreen = "role"
                                }
                            )
                        }

                        selectedRole == "user" &&
                                currentScreen == "user_dashboard" -> {

                            AlumniMainScreen(

                                onLogout = {

                                    selectedRole = ""
                                    currentScreen = "role"
                                }
                            )
                        }

                        else -> {

                            LoginScreen(

                                onLoginSuccess = {

                                    if (selectedRole == "admin") {

                                        currentScreen =
                                            "admin_dashboard"

                                    } else {

                                        currentScreen =
                                            "user_dashboard"
                                    }
                                },

                                onNavigateToSignup = {

                                    currentScreen = "signup"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
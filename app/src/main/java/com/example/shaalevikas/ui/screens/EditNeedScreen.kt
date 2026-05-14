package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shaalevikas.model.Need
import com.example.shaalevikas.repository.NeedRepository

@Composable
fun EditNeedScreen(

    need: Need,

    onUpdateComplete: () -> Unit

) {

    val repository = NeedRepository()

    var title by remember {
        mutableStateOf(need.title)
    }

    var description by remember {
        mutableStateOf(need.description)
    }

    var targetAmount by remember {
        mutableStateOf(
            need.targetAmount.toString()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Edit Need",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {

                title = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {

                Text("Title")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {

                description = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {

                Text("Description")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = targetAmount,
            onValueChange = {

                targetAmount = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {

                Text("Target Amount")
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                val updatedNeed = need.copy(

                    title = title,

                    description = description,

                    targetAmount =
                        targetAmount.toInt()
                )

                repository.updateNeed(
                    updatedNeed
                )

                onUpdateComplete()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4F46E5)
            )
        ) {

            Text(
                text = "Update Need"
            )
        }
    }
}
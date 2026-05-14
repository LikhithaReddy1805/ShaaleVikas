package com.example.shaalevikas.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shaalevikas.model.Need
import com.example.shaalevikas.repository.NeedRepository
import androidx.compose.foundation.layout.width
@Composable
fun AddNeedScreen() {

    val context = LocalContext.current

    val repository = remember {
        NeedRepository()
    }

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var targetAmount by remember {
        mutableStateOf("")
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Add School Need",
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
                Text("Need Title")
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

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                launcher.launch("image/*")

            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF14B8A6)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Pick Image")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (imageUri != null) {

            Card(
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = {

                if (
                    title.isEmpty() ||
                    description.isEmpty() ||
                    targetAmount.isEmpty()
                ) {

                    Toast.makeText(
                        context,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val need = Need(

                    title = title,

                    description = description,

                    targetAmount = targetAmount.toInt(),

                    collectedAmount = 0,

                    imageUrl = imageUri?.toString() ?: ""
                )

                repository.addNeed(

                    need = need,

                    onSuccess = {

                        Toast.makeText(
                            context,
                            "Need Added Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        title = ""
                        description = ""
                        targetAmount = ""
                        imageUri = null

                    },

                    onFailure = {

                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
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
                text = "Add Need"
            )
        }
    }
}
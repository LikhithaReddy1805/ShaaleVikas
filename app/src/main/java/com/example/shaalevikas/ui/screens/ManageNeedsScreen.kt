package com.example.shaalevikas.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shaalevikas.model.Need
import com.example.shaalevikas.repository.NeedRepository
import com.example.shaalevikas.viewmodel.NeedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageNeedsScreen(
    viewModel: NeedViewModel = viewModel()
) {

    val needs = viewModel.needs

    var selectedNeed by remember {
        mutableStateOf<Need?>(null)
    }

    if (selectedNeed != null) {

        EditNeedScreen(

            need = selectedNeed!!,

            onUpdateComplete = {

                selectedNeed = null
            }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
        ) {

            TopAppBar(
                title = {

                    Text(
                        text = "Manage School Needs",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F46E5)
                )
            )

            LazyColumn(
                modifier = Modifier.padding(14.dp)
            ) {

                items(needs) { need ->

                    ManageNeedCard(

                        need = need,

                        onEditClick = {

                            selectedNeed = need
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ManageNeedCard(

    need: Need,

    onEditClick: () -> Unit

) {

    val repository = NeedRepository()

    val progress =
        if (need.targetAmount > 0)
            need.collectedAmount.toFloat() /
                    need.targetAmount.toFloat()
        else
            0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = need.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Row {

                    IconButton(
                        onClick = {

                            onEditClick()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color(0xFF4F46E5)
                        )
                    }

                    IconButton(
                        onClick = {

                            repository.deleteNeed(
                                need.id
                            )
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = need.description,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(18.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50)),
                color = Color(0xFF14B8A6)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text =
                    "₹${need.collectedAmount} raised of ₹${need.targetAmount}",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
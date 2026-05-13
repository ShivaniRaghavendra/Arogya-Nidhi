package com.example.arogyanidhi.ui.eligibility

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EligibilityScreen(
    viewModel: EligibilityViewModel,
    onNavigateBack: () -> Unit
) {
    val eligibilityData by viewModel.eligibilityData.collectAsState()
    val eligibleSchemes by viewModel.eligibleSchemes.collectAsState()

    var currentStep by remember { mutableStateOf(0) }
    var hasChecked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eligibility Checker") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // Show form before eligibility check
            if (!hasChecked) {

                when (currentStep) {

                    0 -> {
                        Text(
                            text = "Step 1: Financial Information",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = if (eligibilityData.income == 0.0) "" else eligibilityData.income.toString(),
                            onValueChange = {
                                viewModel.updateIncome(it.toDoubleOrNull() ?: 0.0)
                            },
                            label = { Text("Annual Income") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = eligibilityData.isBpl,
                                onCheckedChange = {
                                    viewModel.updateBpl(it)
                                }
                            )
                            Text(text = "BPL Status")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                currentStep = 1
                            }
                        ) {
                            Text("Next")
                        }
                    }

                    1 -> {
                        Text(
                            text = "Step 2: Professional Information",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = eligibilityData.occupation,
                            onValueChange = {
                                viewModel.updateOccupation(it)
                            },
                            label = { Text("Occupation") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                hasChecked = true
                                viewModel.checkEligibility()
                            }
                        ) {
                            Text("Check Eligibility")
                        }
                    }
                }

            } else {

                // If checked but no schemes found
                if (eligibleSchemes.isEmpty()) {

                    Text(
                        text = "No eligible schemes found based on your details",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            hasChecked = false
                            currentStep = 0
                        }
                    ) {
                        Text("Try Again")
                    }

                } else {

                    // Show eligible schemes
                    Text(
                        text = "Eligible Schemes",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        items(eligibleSchemes) { scheme ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = scheme.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = scheme.description
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            onNavigateBack()
                        }
                    ) {
                        Text("Finish")
                    }
                }
            }
        }
    }
}
package com.jonas.cookly.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavHelper(
    shouldNavigate: () -> Boolean,
    destination: () -> Unit
) {
    LaunchedEffect(key1 = shouldNavigate()) {
        if (shouldNavigate()) {
            destination()
        }
    }
}
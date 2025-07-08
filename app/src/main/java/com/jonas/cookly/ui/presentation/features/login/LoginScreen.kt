package com.jonas.cookly.ui.presentation.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.ui.theme.CooklyAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        Arrangement.Center
    ) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = onNavigateToRegister
        ) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    CooklyAppTheme {
        LoginScreen(
            onNavigateToRegister = {}
        )
    }
}
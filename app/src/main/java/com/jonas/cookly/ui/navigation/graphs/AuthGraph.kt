package com.jonas.cookly.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jonas.cookly.ui.features.login.presentation.LoginScreen
import com.jonas.cookly.ui.features.register.presentation.RegisterScreen
import com.jonas.cookly.ui.navigation.screens.AuthScreens
import com.jonas.cookly.ui.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {
        composable<AuthScreens.RegisterScreen> {
            RegisterScreen(
                onNavigateToLogin = onNavigateToLogin
            )
        }

        composable<AuthScreens.LoginScreen> {
            LoginScreen(
                onNavigateToRegister = onNavigateToRegister,
                onNavigateToHome = onNavigateToHome
            )
        }
    }
}

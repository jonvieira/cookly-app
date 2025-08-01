package com.jonas.cookly.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jonas.cookly.ui.presentation.navigation.graphs.authGraph
import com.jonas.cookly.ui.presentation.navigation.graphs.homeGraph
import com.jonas.cookly.ui.presentation.navigation.screens.AuthScreens
import com.jonas.cookly.ui.presentation.navigation.screens.Graphs

@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToRegister = {
                navController.navigate(AuthScreens.RegisterScreen)
            },
            onNavigateToLogin = {
                navController.navigate(AuthScreens.LoginScreen) {
                    popUpTo(Graphs.AuthGraph) {
                        inclusive = true
                    }
                }
            }
        )

        homeGraph()
    }
}

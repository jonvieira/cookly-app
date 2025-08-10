package com.jonas.cookly.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jonas.cookly.ui.presentation.features.home.HomeScreen
import com.jonas.cookly.ui.presentation.navigation.screens.Graphs
import com.jonas.cookly.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.HomeScreen
    ) {
        composable<HomeScreens.HomeScreen> {
            HomeScreen(
                onNavigateUp = onNavigateUp
            )
        }
    }
}

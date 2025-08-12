package com.jonas.cookly.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jonas.cookly.ui.features.home.HomeScreen
import com.jonas.cookly.ui.navigation.screens.Graphs
import com.jonas.cookly.ui.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph() {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.HomeScreen
    ) {
        composable<HomeScreens.HomeScreen> {
            HomeScreen()
        }
    }
}

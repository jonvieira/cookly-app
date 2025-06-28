package com.jonas.cookly.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.jonas.cookly.presentation.navigation.screens.AuthScreens
import com.jonas.cookly.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHome: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {

    }
}
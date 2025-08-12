package com.jonas.cookly.ui

import com.jonas.cookly.ui.navigation.screens.Graphs

data class MainUiState(
    val startDestination: Graphs = Graphs.AuthGraph
)

package com.jonas.cookly.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jonas.cookly.ui.presentation.navigation.RootHost
import com.jonas.cookly.ui.presentation.navigation.screens.Graphs
import com.jonas.cookly.ui.theme.CooklyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            CooklyAppTheme {
                RootHost(
                    startDestination = Graphs.AuthGraph,
                    navController = rememberNavController()
                )
            }
        }
    }
}

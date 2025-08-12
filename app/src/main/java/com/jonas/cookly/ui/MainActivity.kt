package com.jonas.cookly.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.jonas.cookly.ui.navigation.RootHost
import com.jonas.cookly.ui.theme.CooklyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isSplashLoading.value
        }

        setContent {
            CooklyAppTheme {

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val startDestination = uiState.startDestination

                RootHost(
                    startDestination = startDestination,
                    navController = rememberNavController()
                )
            }
        }
    }
}

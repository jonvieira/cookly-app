package com.jonas.cookly.core.util.sideeffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Any> SingleEventEffect(
    sideEffect: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(sideEffect) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            sideEffect.collect(collector)
        }
    }
}

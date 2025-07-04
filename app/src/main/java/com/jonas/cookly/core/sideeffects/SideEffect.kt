package com.jonas.cookly.core.sideeffects

sealed interface SideEffect {
    data class ShowToast(val message: String) : SideEffect
}

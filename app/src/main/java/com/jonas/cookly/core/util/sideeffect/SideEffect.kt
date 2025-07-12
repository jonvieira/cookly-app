package com.jonas.cookly.core.util.sideeffect

sealed interface SideEffect {
    data class ShowToast(val message: String) : SideEffect
}

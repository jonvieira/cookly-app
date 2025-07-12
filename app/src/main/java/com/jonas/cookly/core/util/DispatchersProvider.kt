package com.jonas.cookly.core.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersProvider {
    fun main() = Dispatchers.Main
    fun io() = Dispatchers.IO
    fun default() = Dispatchers.Default
    fun unconfined() = Dispatchers.Unconfined
}

class DispatchersProviderImpl @Inject constructor() : DispatchersProvider

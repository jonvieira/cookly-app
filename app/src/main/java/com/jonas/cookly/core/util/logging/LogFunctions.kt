package com.jonas.cookly.core.util.logging

import timber.log.Timber

fun logError(tag: String, message: String) = Timber.tag(tag).e("Error -> $message")

fun logWarning(tag: String, message: String) = Timber.tag(tag).w("Warning -> $message")

fun logInfo(tag: String, message: String) = Timber.tag(tag).i("Info -> $message")
package com.jonas.cookly.ui.presentation.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jonas.cookly.R

@Composable
fun IconApp(modifier: Modifier = Modifier) {
    val icon = painterResource(
        if (isSystemInDarkTheme()) R.drawable.ic_logo_dark else R.drawable.ic_logo_light
    )

    Image(
        painter = icon,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier.fillMaxWidth()
    )
}

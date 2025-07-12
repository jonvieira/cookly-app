package com.jonas.cookly.ui.presentation.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.ui.presentation.components.Dimens
import com.jonas.cookly.ui.theme.CooklyAppTheme
import com.jonas.cookly.ui.theme.poppinsFontFamily

@Composable
fun AuthButton(
    modifier: Modifier,
    text: String,
    isLoading: Boolean,
    contentColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.Radius.xLarge),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray
        ),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(Dimens.Spacing.mediumLarge)
            )
        } else {
            Text(
                text = text,
                fontSize = Dimens.Font.medium,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthButtonPreview() {
    CooklyAppTheme {
        AuthButton(
            modifier = Modifier,
            text = "Entrar",
            isLoading = false,
            contentColor = Color.White,
            onClick = {}
        )
    }
}

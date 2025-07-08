package com.jonas.cookly.ui.presentation.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.ui.presentation.components.Dimens
import com.jonas.cookly.ui.theme.CooklyAppTheme
import com.jonas.cookly.ui.theme.poppinsFontFamily

@Composable
fun TextEntry(
    modifier: Modifier = Modifier,
    description: String,
    hint: String,
    textColor: Color,
    cursorColor: Color,
    leadingIcon: ImageVector,
    textValue: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = {},
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier,
            value = textValue,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Start
            ),
            label = {
                Text(
                    text = description,
                    color = textColor,
                    fontFamily = poppinsFontFamily
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = poppinsFontFamily
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "icone do text field",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "icone do text field",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable {
                            if (onTrailingIconClick != null) onTrailingIconClick()
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = cursorColor,
                focusedBorderColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextEntryPreview() {
    CooklyAppTheme {
        TextEntry(
            description = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.Spacing.small,
                    end = Dimens.Spacing.small,
                    bottom = Dimens.Spacing.small
                ),
            hint = "teste@gmail.com",
            leadingIcon = Icons.Default.Email,
            textValue = "email@email.com",
            textColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChange = {},
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClick = {},
            visualTransformation = VisualTransformation.Companion.None
        )
    }
}

package com.jonas.cookly.ui.presentation.features.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.R
import com.jonas.cookly.ui.presentation.components.Dimens
import com.jonas.cookly.ui.presentation.components.button.AuthButton
import com.jonas.cookly.ui.presentation.components.textfields.TextEntry
import com.jonas.cookly.ui.presentation.components.transformations.PhoneVisualTransformation
import com.jonas.cookly.ui.theme.CooklyAppTheme

@Composable
fun RegisterContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    nameValue: String,
    emailValue: String,
    phoneValue: String,
    passwordValue: String,
    passwordRepeatedValue: String,
    isPasswordShown: Boolean,
    buttonEnabled: Boolean,
    isPasswordRepeatedShown: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTrailingPasswordIconClick: () -> Unit,
    onTrailingPasswordRepeatedIconClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.medium)
    ) {
        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_name_text),
            hint = stringResource(R.string.hint_nome_text),
            leadingIcon = Icons.Default.Person,
            textValue = nameValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChange = onNameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textValue = emailValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChange = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_phone_text),
            hint = stringResource(R.string.hint_phone_text),
            leadingIcon = Icons.Default.PhoneAndroid,
            textValue = phoneValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChange = { if (it.length <= 11) onPhoneChanged(it) },
            keyboardType = KeyboardType.Phone,
            visualTransformation = PhoneVisualTransformation(),
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChange = onPasswordChanged,
            keyboardType = KeyboardType.Phone,
            visualTransformation = if (isPasswordShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingPasswordIconClick
        )

        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_repeat_password_text),
            hint = stringResource(R.string.hint_repeat_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordRepeatedValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChange = onPasswordRepeatedChanged,
            keyboardType = KeyboardType.Phone,
            visualTransformation = if (isPasswordRepeatedShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onTrailingPasswordRepeatedIconClick
        )

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.tiny)
        ) {
            AuthButton(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(Dimens.Spacing.tiny, RoundedCornerShape(Dimens.Radius.xxLarge)),
                text = stringResource(R.string.register_button_text),
                isLoading = isLoading,
                contentColor = Color.White,
                enabled = buttonEnabled,
                onClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContainerPreview() {
    CooklyAppTheme {
        RegisterContainer(
            modifier = Modifier,
            isLoading = false,
            nameValue = "Jonas Vieira",
            emailValue = "jonasvieira.ti@gmail.com",
            phoneValue = "11968284673",
            passwordValue = "12345",
            passwordRepeatedValue = "12345",
            isPasswordShown = false,
            buttonEnabled = true,
            isPasswordRepeatedShown = false,
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onPasswordRepeatedChanged = {},
            onButtonClick = {},
            onTrailingPasswordIconClick = {},
            onTrailingPasswordRepeatedIconClick = {}
        )
    }
}

package com.jonas.cookly.ui.features.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.R
import com.jonas.cookly.ui.components.Dimens
import com.jonas.cookly.ui.components.button.AuthButton
import com.jonas.cookly.ui.components.textfields.TextEntry
import com.jonas.cookly.ui.theme.CooklyAppTheme

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    emailValue: String,
    passwordValue: String,
    buttonEnabled: Boolean,
    isPasswordVisible: Boolean,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onTogglePasswordIconClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(modifier = modifier.padding(Dimens.Spacing.medium)) {
        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textValue = emailValue,
            onValueChange = onEmailValueChange,
            trailingIcon = null,
            onTrailingIconClick = null,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )

        TextEntry(
            modifier = modifier.fillMaxWidth(),
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue,
            onValueChange = onPasswordValueChange,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = { onTogglePasswordIconClick() },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = modifier.size(Dimens.Spacing.large))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .shadow(Dimens.Spacing.tiny, RoundedCornerShape(Dimens.Spacing.large)),
            verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.small)
        ) {
            AuthButton(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.login_text),
                isLoading = isLoading,
                enabled = buttonEnabled,
                onClick = onLoginClick,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    CooklyAppTheme {
        LoginContainer(
            modifier = Modifier,
            isLoading = false,
            emailValue = "",
            passwordValue = "",
            buttonEnabled = true,
            isPasswordVisible = false,
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onTogglePasswordIconClick = {},
            onLoginClick = {}
        )
    }
}
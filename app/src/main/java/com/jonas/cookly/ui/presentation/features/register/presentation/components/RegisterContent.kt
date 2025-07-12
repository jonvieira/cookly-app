package com.jonas.cookly.ui.presentation.features.register.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jonas.cookly.R
import com.jonas.cookly.ui.presentation.components.Dimens
import com.jonas.cookly.ui.presentation.components.images.IconApp
import com.jonas.cookly.ui.presentation.features.register.presentation.state.RegisterUserState
import com.jonas.cookly.ui.theme.CooklyAppTheme
import com.jonas.cookly.ui.theme.poppinsFontFamily

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    uiState: RegisterUserState,
    onNavigateToLogin: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onTogglePasswordIconClick: () -> Unit,
    onTogglePasswordRepeatedIconClick: () -> Unit,
    onRegisterButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconApp(
            modifier = modifier
                .padding(
                    top = Dimens.Spacing.large,
                    start = Dimens.Spacing.large,
                    end = Dimens.Spacing.large
                )
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.register_text),
                    fontFamily = poppinsFontFamily,
                    fontSize = Dimens.Font.large,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifier.fillMaxWidth()
                )
                Text(
                    text = uiState.errorMessage ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFontFamily,
                    fontSize = Dimens.Font.regular,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.Spacing.tiny)
                )
            }

            RegisterContainer(
                modifier = modifier
                    .padding(
                        start = Dimens.Spacing.small,
                        end = Dimens.Spacing.small
                    )
                    .fillMaxWidth(),
                isLoading = uiState.isLoading,
                nameValue = uiState.nameInput,
                emailValue = uiState.emailInput,
                phoneValue = uiState.phoneInput,
                passwordValue = uiState.passwordInput,
                passwordRepeatedValue = uiState.passwordConfirmationInput,
                buttonEnabled = uiState.isInputValid,
                isPasswordShown = uiState.isPasswordVisible,
                isPasswordRepeatedShown = uiState.isPasswordConfirmationVisible,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onTrailingPasswordIconClick = onTogglePasswordIconClick,
                onTrailingPasswordRepeatedIconClick = onTogglePasswordRepeatedIconClick,
                onButtonClick = onRegisterButtonClick
            )

            Row(
                modifier = modifier.padding(
                    top = Dimens.Spacing.small,
                    bottom = Dimens.Spacing.large
                ),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontSize = Dimens.Font.regular,
                    fontFamily = poppinsFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(R.string.login_text),
                    fontSize = Dimens.Font.regular,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifier
                        .padding(start = Dimens.Spacing.tiny)
                        .clickable { onNavigateToLogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContentPreview() {
    CooklyAppTheme {
        RegisterContent(
            paddingValues = PaddingValues(),
            uiState = RegisterUserState(),
            onNavigateToLogin = {},
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onPasswordRepeatedChanged = {},
            onTogglePasswordIconClick = {},
            onTogglePasswordRepeatedIconClick = {},
            onRegisterButtonClick = {}
        )
    }
}

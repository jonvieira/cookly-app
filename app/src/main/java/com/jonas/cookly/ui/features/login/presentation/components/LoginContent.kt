package com.jonas.cookly.ui.features.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.jonas.cookly.ui.components.Dimens
import com.jonas.cookly.ui.components.images.IconApp
import com.jonas.cookly.ui.features.login.presentation.state.LoginUiState
import com.jonas.cookly.ui.theme.CooklyAppTheme
import com.jonas.cookly.ui.theme.poppinsFontFamily

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    paddingValues: PaddingValues,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onTogglePasswordIconClick: () -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconApp(
            modifier = Modifier
                .padding(Dimens.Spacing.large)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_text),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = Dimens.Font.xlarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.errorMessageLoginProcess ?: uiState.errorMessageInput.orEmpty(),
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(Dimens.Spacing.large))

        LoginContainer(
            modifier = Modifier.fillMaxWidth(),
            isLoading = uiState.isLoading,
            emailValue = uiState.email,
            passwordValue = uiState.password,
            buttonEnabled = uiState.isInputValid,
            isPasswordVisible = uiState.isPasswordVisible,
            onEmailValueChange = onEmailValueChange,
            onPasswordValueChange = onPasswordValueChange,
            onTogglePasswordIconClick = onTogglePasswordIconClick,
            onLoginClick = onLoginClick
        )

        Row(
            modifier = Modifier.padding(
                top = Dimens.Spacing.small,
                bottom = Dimens.Spacing.large
            ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.no_have_account_text),
                fontSize = Dimens.Font.regular,
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.register_text),
                fontSize = Dimens.Font.regular,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = modifier
                    .padding(start = Dimens.Spacing.tiny)
                    .clickable { onNavigateToRegister() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    CooklyAppTheme {
        LoginContent(
            modifier = Modifier,
            paddingValues = PaddingValues(),
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onTogglePasswordIconClick = {},
            onLoginClick = {},
            onNavigateToRegister = {},
            uiState = LoginUiState()
        )
    }
}
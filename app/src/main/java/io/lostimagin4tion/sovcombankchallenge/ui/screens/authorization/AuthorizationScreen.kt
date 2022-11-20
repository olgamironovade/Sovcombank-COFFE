package io.lostimagin4tion.sovcombankchallenge.ui.screens.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.components.dividers.Divider
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankGradientBackground
import io.lostimagin4tion.sovcombankchallenge.ui.utils.ErrorResult
import io.lostimagin4tion.sovcombankchallenge.ui.utils.SuccessResult

@Composable
fun AuthorizationScreen(
    navController: NavController,
    showMessage: (Int) -> Unit,
    showTextMessage: (String) -> Unit
) {
    val viewModel: AuthorizationViewModel = viewModel()

    val loginResult by viewModel.loginResult.collectAsState()
    loginResult.also {
        when(it) {
            is ErrorResult -> showMessage(it.message!!)
            is SuccessResult -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.home) {
                        popUpTo(Routes.authorization) { inclusive = true }
                    }
                }
            }
            else -> {}
        }
    }

    AuthorizationScreenContent(viewModel::login, navController, showTextMessage)
}

@Composable
fun AuthorizationScreenContent(
    login: (username: String, password: String) -> Unit = { _, _ -> },
    navController: NavController? = null,
    showTextMessage: (String) -> Unit = {}
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .background(
            brush = Brush.verticalGradient(colors = sovcombankGradientBackground)
        )
) {
    val alertMessage = stringResource(R.string.auth_alert_message)
    val context = LocalContext.current

    var emailInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    val isLoginButtonEnabled = emailInput.text.isNotEmpty() && passwordInput.text.isNotEmpty()

    Image(
        painter = painterResource(R.drawable.logo_sovcombank_white),
        contentDescription = null,
        modifier = Modifier
            .padding(
                top = 100.dp,
                bottom = 75.dp
            )
            .size(80.dp),
    )

    CommonTextField(
        value = emailInput,
        labelId = R.string.auth_email,
        leadingIconId = R.drawable.ic_email,
        trailingIconId = R.drawable.ic_clear,
        onValueChange = {
            emailInput = it
        },
    )

    CommonTextField(
        value = passwordInput,
        labelId = R.string.auth_password,
        trailingIconId = R.drawable.ic_clear,
        onValueChange = {
            passwordInput = it
        },
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation(),
    )

    TextFilledButton(
        onClick = {
            if (!isLoginButtonEnabled) {
                showTextMessage(alertMessage)
            }
            else {
                navController?.navigate(Routes.home)
//                login(emailInput.text, passwordInput.text)
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 75.dp)
            .padding(horizontal = 36.dp),
        textId = R.string.auth_login_action
    )

    Divider(
        textModifier = Modifier.padding(horizontal = 10.dp),
        paddingModifier = Modifier.padding(vertical = 15.dp),
        textResource = R.string.auth_divider_text
    )

    TextFilledButton(
        onClick = { navController?.navigate(Routes.registration) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        textId = R.string.auth_registration_action
    )

    TextButton(
        onClick = { navController?.navigate(Routes.passwordRecovery) },
        modifier = Modifier.padding(top = 50.dp)
    ) {
        Text(
            text = stringResource(R.string.auth_forgot_password),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
fun AuthorizationScreenPreview() = SovcombankChallengeTheme {
    AuthorizationScreenContent()
}
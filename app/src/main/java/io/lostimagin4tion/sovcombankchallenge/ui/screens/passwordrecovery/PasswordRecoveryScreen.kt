package io.lostimagin4tion.sovcombankchallenge.ui.screens.passwordrecovery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun PasswordRecoveryScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    PasswordRecoveryScreenContent(navController)
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun PasswordRecoveryScreenContent(
    navController: NavController? = null
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
) {
    val alertMessage = stringResource(R.string.auth_alert_message)
    val context = LocalContext.current
    val density = LocalDensity.current

    var emailInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var codeInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var repeatPasswordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var isEmailSubmitted by rememberSaveable {
        mutableStateOf(false)
    }
    var isCodeSubmitted by rememberSaveable() {
        mutableStateOf(false)
    }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { navController?.popBackStack() }) {
            Icon(
                painter = painterResource(R.drawable.ic_back_button),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        Text(
            text = stringResource(R.string.password_recovery_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    Text(
        text = stringResource(R.string.password_recovery_body),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp)
            .align(Alignment.Start)
    )

    CommonTextField(
        value = emailInput,
        labelId = R.string.password_recovery_email,
        leadingIconId = R.drawable.ic_email,
        trailingIconId = R.drawable.ic_clear,
        onValueChange = {
            emailInput = it
        },
    )

    AnimatedVisibility(
        visible = isEmailSubmitted,
        enter = scaleIn(),
        exit = scaleOut(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.code_confirmation_body),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )

            CommonTextField(
                value = codeInput,
                labelId = R.string.code_confirmation_label,
                onValueChange = {
                    codeInput = it
                },
                keyboardType = KeyboardType.Number
            )
        }
    }

    AnimatedVisibility(
        visible = isCodeSubmitted,
        enter = scaleIn(),
        exit = scaleOut(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.password_recovery_end),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )

            CommonTextField(
                value = passwordInput,
                labelId = R.string.registration_password,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    passwordInput = it
                },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )

            CommonTextField(
                value = repeatPasswordInput,
                labelId = R.string.registration_password_repeat,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    repeatPasswordInput = it
                },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }

    TextFilledButton(
        onClick = {
            if (isCodeSubmitted) {
                navController?.navigate(Routes.authorization)
            }
            else if (isEmailSubmitted) {
                isCodeSubmitted = true
            }
            else {
                isEmailSubmitted = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp)
            .padding(top = 30.dp),
        textId = if (!isCodeSubmitted) R.string.password_recovery_action else R.string.code_confirmation_submit
    )

    AnimatedVisibility(
        visible = isEmailSubmitted && !isCodeSubmitted,
        enter = scaleIn(),
        exit = scaleOut(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        TextButton(
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = stringResource(R.string.code_confirmation_resend),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PasswordRecoveryScreenPreview() = SovcombankChallengeTheme {
    PasswordRecoveryScreenContent()
}
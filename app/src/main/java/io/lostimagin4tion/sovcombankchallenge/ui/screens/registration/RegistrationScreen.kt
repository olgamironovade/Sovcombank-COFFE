package io.lostimagin4tion.sovcombankchallenge.ui.screens.registration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun RegistrationScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    RegistrationScreenContent(navController)
}

@Composable
fun RegistrationScreenContent(
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

    var secondNameInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var nameInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var patronymicInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var birthDateInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var emailInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var phoneNumberInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var passwordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var repeatPasswordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var isNextButtonClicked by rememberSaveable { mutableStateOf(false) }

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
            text = stringResource(R.string.registration_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    AnimatedVisibility(
        visible = !isNextButtonClicked,
        enter = slideInHorizontally { fullWidth -> -fullWidth },
        exit = slideOutHorizontally { fullWidth -> -fullWidth },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            CommonTextField(
                value = secondNameInput,
                labelId = R.string.registration_second_name,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    secondNameInput = it
                },
            )

            CommonTextField(
                value = nameInput,
                labelId = R.string.registration_first_name,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    nameInput = it
                },
            )

            CommonTextField(
                value = patronymicInput,
                labelId = R.string.registration_patronymic,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    patronymicInput = it
                },
            )

            CommonTextField(
                value = birthDateInput,
                labelId = R.string.registration_birth_date,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    birthDateInput = it
                },
            )

            TextFilledButton(
                onClick = { isNextButtonClicked = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
                    .padding(top = 50.dp),
                textId = R.string.registration_next_action
            )
        }
    }

    AnimatedVisibility(
        visible = isNextButtonClicked,
        enter = slideInHorizontally { fullWidth -> fullWidth },
        exit = slideOutHorizontally { fullWidth -> fullWidth },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            CommonTextField(
                value = emailInput,
                labelId = R.string.registration_email,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    emailInput = it
                },
            )

            CommonTextField(
                value = phoneNumberInput,
                labelId = R.string.registration_phone_number,
                trailingIconId = R.drawable.ic_clear,
                onValueChange = {
                    phoneNumberInput = it
                },
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

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
                    .padding(top = 50.dp)
            ) {
                TextFilledButton(
                    onClick = { isNextButtonClicked = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black
                    ),
                    textId = R.string.registration_back_action,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(width = 100.dp, height = 50.dp)
                )

                TextFilledButton(
                    onClick = { navController?.navigate(Routes.codeConfirmation) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black
                    ),
                    textId = R.string.registration_action,
                    modifier = Modifier.size(width = 160.dp, height = 50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun RegistrationScreenPreview() = SovcombankChallengeTheme {
    RegistrationScreenContent()
}
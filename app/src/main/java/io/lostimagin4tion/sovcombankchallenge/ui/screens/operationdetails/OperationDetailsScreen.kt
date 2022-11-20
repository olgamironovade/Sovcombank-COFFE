package io.lostimagin4tion.sovcombankchallenge.ui.screens.operationdetails

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.components.selectors.DropDownSelector
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun OperationDetailsScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    OperationDetailsScreenContent(navController)
}

@Composable
fun OperationDetailsScreenContent(
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

    var accountInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var amountInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
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
            text = stringResource(R.string.operation_details_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    DropDownSelector(
        value = accountInput,
        labelId = R.string.operation_details_account,
        onValueChange = { newValue: TextFieldValue ->
            accountInput = newValue
        },
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(top = 50.dp)
    )

    CommonTextField(
        value = amountInput,
        labelId = R.string.operation_details_amount,
        trailingIconId = R.drawable.ic_clear,
        onValueChange = {
            amountInput = it
        },
    )

    TextFilledButton(
        onClick = { navController?.navigate(Routes.codeConfirmation) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        textId = R.string.operation_details_action,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .padding(top = 50.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun OperationDetailsPreview() = SovcombankChallengeTheme {
    OperationDetailsScreenContent()
}
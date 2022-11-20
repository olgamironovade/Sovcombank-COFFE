package io.lostimagin4tion.sovcombankchallenge.ui.screens.accountinfo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemService
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.components.selectors.DropDownSelector
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun AccountInfoScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    AccountInfoScreenContent(navController)
}


@Composable
fun AccountInfoScreenContent(
    navController: NavController? = null
) = Column(
    horizontalAlignment = Alignment.Start,
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
            .padding(vertical = 50.dp)
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
            text = stringResource(R.string.services_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        ItemService(
            iconId = R.drawable.ic_requisites,
            labelId = R.string.info_requisites,
            onClick = {}
        )

        ItemService(
            iconId = R.drawable.ic_statement,
            labelId = R.string.info_statement,
            onClick = {}
        )

        ItemService(
            iconId = R.drawable.ic_reference,
            labelId = R.string.info_reference,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AccountInfoScreenPreview() = SovcombankChallengeTheme {
    AccountInfoScreenContent()
}
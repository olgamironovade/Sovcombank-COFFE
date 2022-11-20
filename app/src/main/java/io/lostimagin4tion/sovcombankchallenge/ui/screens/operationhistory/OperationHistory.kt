package io.lostimagin4tion.sovcombankchallenge.ui.screens.operationhistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemHistoryDay
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemHistoryInfo
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun OperationHistoryScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    OperationHistoryScreenContent(navController)
}

@Composable
fun OperationHistoryScreenContent(
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

    var firstCurrencyInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("RUB"))
    }
    var secondCurrencyInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("USD"))
    }

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        val (logo, profileButton, servicesButton, backButton) = createRefs()

        Image(
            painter =
            if (isSystemInDarkTheme())
                painterResource(R.drawable.logo_sovcombank_white)
            else
                painterResource(R.drawable.logo_sovcombank),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }
                .size(40.dp),
        )

        IconButton(
            onClick = { navController?.navigate(Routes.profile) },
            modifier = Modifier
                .constrainAs(profileButton) {
                    end.linkTo(servicesButton.start)
                    centerVerticallyTo(logo)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        IconButton(
            onClick = { navController?.navigate(Routes.services) },
            modifier = Modifier
                .constrainAs(servicesButton) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(logo)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_services),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        IconButton(
            onClick = { navController?.popBackStack() },
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(logo)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back_button),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
    ) {
        item {
            ItemHistoryDay(text = "Сегодня")
        }

        item {
            ItemHistoryInfo(
                time = "15:09",
                name = "Татьяна В.",
                account = "Счёт *8732",
                amount = "+ 3200"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AccountDetailsScreenPreview() = SovcombankChallengeTheme {
    OperationHistoryScreenContent()
}
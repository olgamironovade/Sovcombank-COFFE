package io.lostimagin4tion.sovcombankchallenge.ui.screens.trading

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.components.selectors.DropDownSelector
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankGray

@Composable
fun TradingScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    TradingScreenContent(navController)
}

@Composable
fun TradingScreenContent(
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
        val (logo, profileButton, servicesButton) = createRefs()

        Image(
            painter =
            if (isSystemInDarkTheme())
                painterResource(R.drawable.logo_sovcombank_white)
            else
                painterResource(R.drawable.logo_sovcombank),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {
                    start.linkTo(parent.start)
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
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp)
            .padding(horizontal = 10.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(140.dp)
        ) {
            DropDownSelector(
                value = firstCurrencyInput,
                labelId = R.string.trading_sell,
                onValueChange = { newValue: TextFieldValue ->
                    firstCurrencyInput = newValue
                },
            )
        }

        IconButton(
            onClick = {
                val temp = TextFieldValue(firstCurrencyInput.text)
                firstCurrencyInput = secondCurrencyInput
                secondCurrencyInput = temp
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_swap),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(140.dp)
        ) {
            DropDownSelector(
                value = secondCurrencyInput,
                labelId = R.string.trading_buy,
                onValueChange = { newValue: TextFieldValue ->
                    firstCurrencyInput = newValue
                },
            )
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp)
    ) {
        val (firstPrice, time, secondPrice) = createRefs()

        Text(
            text = "1 $",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .constrainAs(firstPrice) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = "Актуально на ...",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .constrainAs(time) {
                    start.linkTo(parent.start)
                    top.linkTo(firstPrice.bottom)
                }
                .padding(top = 5.dp)
        )

        Text(
            text = "61,65 RUB",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .constrainAs(secondPrice) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )
    }

    TextFilledButton(
        onClick = { navController?.navigate(Routes.operationDetails) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        textId = R.string.trading_action,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .padding(top = 50.dp)
    )

    Text(
        text = stringResource(R.string.trading_news),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(top = 50.dp)
    )

    TextButton(
        onClick = { navController?.navigate(Routes.newAccount) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Text(
            text = stringResource(R.string.trading_new_account),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(end = 20.dp)
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun TradingScreenPreview() = SovcombankChallengeTheme {
    TradingScreenContent()
}
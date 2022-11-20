package io.lostimagin4tion.sovcombankchallenge.ui.screens.accountdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.cards.CardWithIcon
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemAccountCard
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankBlue
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankRed

@Composable
fun AccountDetailsScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    AccountDetailsScreenContent(navController)
}

@Composable
fun AccountDetailsScreenContent(
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
        val (logo, profileButton, servicesButton, basicInfo, backButton) = createRefs()

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

        ItemAccountCard(
            labelText = stringResource(R.string.home_currency_card_rubbles),
            currencyText = "12121",
            currencyPicId = R.drawable.ic_rubble,
            circleColor = Color.Blue,
            modifier = Modifier
                .constrainAs(basicInfo) {
                    centerHorizontallyTo(parent)
                    top.linkTo(logo.bottom)
                }
                .width(300.dp)
                .padding(top = 50.dp)
        )
    }

    Row(
        modifier = Modifier
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp)
    ) {
        CardWithIcon(
            labelId = R.string.account_details_transfer,
            iconId = R.drawable.ic_transfer,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)
                .clickable { navController?.navigate(Routes.transfer) }
        )

        CardWithIcon(
            labelId = R.string.account_details_top_up,
            iconId = R.drawable.ic_top_up,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .padding(start = 15.dp)
                .size(width = 150.dp, height = 100.dp)
                .clickable { navController?.navigate(Routes.topUp) }
        )
    }

    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .padding(horizontal = 20.dp)
    ) {
        CardWithIcon(
            labelId = R.string.account_details_edit,
            iconId = R.drawable.ic_edit,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)
                .clickable { navController?.navigate(Routes.editAccount) }
        )

        CardWithIcon(
            labelId = R.string.account_details_info,
            iconId = R.drawable.ic_info,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .padding(start = 15.dp)
                .size(width = 150.dp, height = 100.dp)
                .clickable { navController?.navigate(Routes.accountInfo) }
        )
    }

    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .padding(horizontal = 20.dp)
    ) {
        CardWithIcon(
            labelId = R.string.account_details_history,
            iconId = R.drawable.ic_history,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)
                .clickable { navController?.navigate(Routes.operationHistory) }

        )

        CardWithIcon(
            labelId = R.string.account_details_delete,
            iconId = R.drawable.ic_trash,
            colors = CardDefaults.cardColors(
                containerColor = sovcombankRed,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(start = 15.dp)
                .size(width = 150.dp, height = 100.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AccountDetailsScreenPreview() = SovcombankChallengeTheme {
    AccountDetailsScreenContent()
}
package io.lostimagin4tion.sovcombankchallenge.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextElevatedButton
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemAccountCard
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemAccountCardWithPieChart
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme

@Composable
fun HomeScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    HomeScreenContent(navController)
}

@Composable
fun HomeScreenContent(
    navController: NavController? = null
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
) {
    var areAccountsVisible by rememberSaveable { mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter =
                if (isSystemInDarkTheme())
                    painterResource(R.drawable.logo_sovcombank_white)
                else
                    painterResource(R.drawable.logo_sovcombank),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp),
        )

        Text(
            text = stringResource(R.string.home_screen_greeting),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 15.dp)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        TextElevatedButton(
            onClick = { navController?.navigate(Routes.profile) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = 20.dp
            ),
            textId = R.string.home_screen_profile,
            iconId = R.drawable.ic_profile
        )

        TextElevatedButton(
            onClick = { navController?.navigate(Routes.services) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = 20.dp
            ),
            textId = R.string.home_screen_services,
            iconId = R.drawable.ic_services,
            modifier = Modifier.padding(start = 20.dp)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                top = 30.dp,
                start = 25.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.home_screen_accounts),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )

        IconButton(
            onClick = { areAccountsVisible = !areAccountsVisible },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .padding(start = 15.dp)
        ) {
            Icon(
                painter =
                if (areAccountsVisible)
                    painterResource(R.drawable.ic_hide)
                else
                    painterResource(R.drawable.ic_expand),
                contentDescription = null,
            )
        }

        IconButton(
            onClick = { navController?.navigate(Routes.newAccount) },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        }
    }

    AnimatedVisibility(
        visible = areAccountsVisible,
        enter =  expandVertically(
            animationSpec = spring(
                stiffness = Spring.StiffnessVeryLow
            )
        ) { -it },
        exit = shrinkVertically(
            animationSpec = spring(
                stiffness = Spring.StiffnessVeryLow
            )
        ) { -it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
    ) {
        LazyColumn {
            item {
                ItemAccountCard(
                    labelText = stringResource(R.string.home_currency_card_rubbles),
                    currencyText = "12121",
                    currencyPicId = R.drawable.ic_rubble,
                    circleColor = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable {
                            navController?.navigate(Routes.accountDetails)
                        }
                )
            }
            item {
                ItemAccountCard(
                    labelText = stringResource(R.string.home_currency_card_dollars),
                    currencyText = "121",
                    currencyPicId = R.drawable.ic_dollar,
                    circleColor = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
            }

            item {
                ItemAccountCard(
                    labelText = stringResource(R.string.home_currency_card_euros),
                    currencyText = "121",
                    currencyPicId = R.drawable.ic_euro,
                    circleColor = Color.Magenta,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
            }

            item {
                ItemAccountCardWithPieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeScreenPreview() = SovcombankChallengeTheme {
    HomeScreenContent()
}
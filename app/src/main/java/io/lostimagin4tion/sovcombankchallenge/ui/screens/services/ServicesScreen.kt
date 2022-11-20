package io.lostimagin4tion.sovcombankchallenge.ui.screens.services

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
fun ServicesScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    ServicesScreenContent(navController)
}

enum class Services(@DrawableRes val iconId: Int, @StringRes val resourceId: Int, val route: String) {
    Trading(R.drawable.ic_trading, R.string.trading, Routes.trading),
    News(R.drawable.ic_news, R.string.news, Routes.news),
//    Recommendations(R.drawable.ic_recommendations, R.string.recommendations, Routes.recommendations),
//    Chat(R.drawable.ic_chat, R.string.chat, Routes.chat),
    Futures(R.drawable.ic_dollar, R.string.services_futures, ""),
    Goals(R.drawable.ic_star, R.string.services_goals, ""),
    Deposits(R.drawable.ic_piggy_bank, R.string.services_deposits, ""),
    Budget(R.drawable.ic_wallet, R.string.services_budget, ""),
    Analysis(R.drawable.ic_analysis, R.string.services_analysis, ""),
    Bonus(R.drawable.ic_bonus, R.string.services_bonus, ""),
}


@Composable
fun ServicesScreenContent(
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
            text = stringResource(R.string.services_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        for (item in Services.values()) {
            ItemService(
                iconId = item.iconId,
                labelId = item.resourceId,
                onClick = {
                    if (item.route.isNotEmpty()) {
                        navController?.navigate(item.route)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ServicesScreenPreview() = SovcombankChallengeTheme {
    ServicesScreenContent()
}
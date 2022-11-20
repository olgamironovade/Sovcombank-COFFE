package io.lostimagin4tion.sovcombankchallenge.ui.screens.admin.adminmain


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import io.lostimagin4tion.sovcombankchallenge.ui.components.cards.CardWithIcon
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankBlue

@Composable
fun AdminMainScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    AdminMainScreenContent(navController)
}


@Composable
fun AdminMainScreenContent(
    navController: NavController? = null
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(horizontal = 10.dp)
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 10.dp)
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
            text = stringResource(R.string.admin_main),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        TextElevatedButton(
            onClick = { navController?.navigate(Routes.adminAccounts) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal =30.dp
            ),
            textId = R.string.admin_accounts,
        )

        TextElevatedButton(
            onClick = { navController?.navigate(Routes.adminUsers) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = 42.dp
            ),
            textId = R.string.admin_users,
            modifier = Modifier.padding(start = 15.dp)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(top = 30.dp)
    ) {
        CardWithIcon(
            text = "1250\nновых счетов",
            iconId = R.drawable.ic_wallet,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = sovcombankBlue
            ),
            modifier = Modifier
                .size(width = 150.dp, height = 130.dp)
        )

        CardWithIcon(
            text = "-100\nпользователей",
            iconId = R.drawable.ic_people,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = sovcombankBlue
            ),
            modifier = Modifier
                .size(width = 160.dp, height = 130.dp)
                .padding(start = 15.dp)
        )
    }

    Text(
        text = "Cтатистика",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(top = 50.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AdminMainScreenPreview() = SovcombankChallengeTheme {
    AdminMainScreenContent()
}
package io.lostimagin4tion.sovcombankchallenge.ui.screens.admin.adminusers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.cards.CardWithIcon
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankBlue

@Composable
fun AdminUsersScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    AdminUsersScreenContent(navController)
}


@Composable
fun AdminUsersScreenContent(
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
            text = stringResource(R.string.admin_users),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

    CardWithIcon(
        text = "-100\nпользователей",
        iconId = R.drawable.ic_people,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = sovcombankBlue
        ),
        modifier = Modifier
            .size(width = 160.dp, height = 200.dp)
            .padding(top = 50.dp)
    )

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 50.dp,
                start = 50.dp
            )
    ) {
        Text(
            text = "#",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
        )

        Text(
            text = "ID",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 100.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(start = 50.dp)
        ) {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AdminUsersScreenPreview() = SovcombankChallengeTheme {
    AdminUsersScreenContent()
}
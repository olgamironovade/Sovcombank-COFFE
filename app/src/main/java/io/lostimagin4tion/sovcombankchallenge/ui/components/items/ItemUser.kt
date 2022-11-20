package io.lostimagin4tion.sovcombankchallenge.ui.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankRed

@Composable
fun ItemUser(
    number: String,
    userId: String,
    onDeleteClick: () -> Unit
) {
    Row {
        Text(
            text = number,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
        )

        Text(
            text = number,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 100.dp)
        )

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.padding(start = 50.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_ban_user),
                contentDescription = null,
                tint = sovcombankRed
            )
        }
    }
}
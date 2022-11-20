package io.lostimagin4tion.sovcombankchallenge.ui.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankBlue
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankRed

@Composable
fun ItemHistoryInfo(
    time: String,
    name: String,
    account: String,
    amount: String
) = ConstraintLayout(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
) {
    val (timeRef, logoRef, nameRef, accountRef, amountRef) = createRefs()

    Text(
        text = time,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .constrainAs(timeRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
    )

    Image(
        painter = painterResource(R.drawable.logo_sovcombank),
        contentDescription = null,
        modifier = Modifier
            .constrainAs(logoRef) {
                top.linkTo(timeRef.bottom)
                centerHorizontallyTo(timeRef)
            }
            .size(28.dp)
            .padding(top = 5.dp)
    )

    Text(
        text = name,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .constrainAs(nameRef) {
                centerVerticallyTo(logoRef)
                start.linkTo(logoRef.end)
            }
            .padding(start = 20.dp)
    )

    Text(
        text = account,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .constrainAs(accountRef) {
                start.linkTo(nameRef.start)
                top.linkTo(nameRef.bottom)
            }
            .padding(
                start = 20.dp,
                top = 2.dp
            )
    )

    Text(
        text = amount,
        style = MaterialTheme.typography.bodyMedium,
        color = if (amount[0] == '-') sovcombankRed else MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .constrainAs(amountRef) {
                end.linkTo(parent.end)
                centerVerticallyTo(nameRef)
            }
            .padding(top = 2.dp)
    )
}
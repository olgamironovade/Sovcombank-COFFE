package io.lostimagin4tion.sovcombankchallenge.ui.components.items

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankGray

@Composable
fun ItemAccountCard(
    modifier: Modifier = Modifier,
    labelText: String,
    currencyText: String,
    @DrawableRes currencyPicId: Int,
    circleColor: Color
) = Card(
    shape = MaterialTheme.shapes.small,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimary
    ),
    elevation = CardDefaults.cardElevation(5.dp),
    modifier = modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(
                vertical = 10.dp,
                horizontal = 10.dp
            )
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (canvas, accountName, accountNumber) = createRefs()

            Canvas(
                modifier = Modifier
                    .constrainAs(canvas) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(accountName)
                    }
                    .size(10.dp)
            ) {
                drawCircle(
                    color = circleColor,
                    radius = 5.dp.toPx()
                )
            }

            Text(
                text = labelText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .constrainAs(accountName) {
                        start.linkTo(canvas.end)
                        top.linkTo(parent.top)
                    }
                    .padding(start = 10.dp)
            )

            Text(
                text = "*5634",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .constrainAs(accountNumber) {
                        end.linkTo(parent.end)
                        centerVerticallyTo(accountName)
                    }
                    .padding(start = 10.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Icon(
                painter = painterResource(currencyPicId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(start = 15.dp)
            )

            Text(
                text = currencyText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(start = 2.dp)
            )
        }
    }
}
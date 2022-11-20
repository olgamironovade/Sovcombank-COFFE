package io.lostimagin4tion.sovcombankchallenge.ui.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.components.buttons.TextFilledButton
import io.lostimagin4tion.sovcombankchallenge.ui.components.items.ItemService
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.Routes
import io.lostimagin4tion.sovcombankchallenge.ui.components.selectors.DropDownSelector
import io.lostimagin4tion.sovcombankchallenge.ui.components.textfields.CommonTextField
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankBlue
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankRed

@Composable
fun ProfileScreen(
    navController: NavController,
    showMessage: (Int) -> Unit
) {
    ProfileScreenContent(navController)
}


@Composable
fun ProfileScreenContent(
    navController: NavController? = null
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
) {
    val alertMessage = stringResource(R.string.auth_alert_message)
    val context = LocalContext.current
    val activity = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        val (backButton, title, exitButton, adminButton) = createRefs()

        IconButton(
            onClick = { navController?.popBackStack() },
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(40.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back_button),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }

        Text(
            text = stringResource(R.string.profile_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(backButton.end)
                    top.linkTo(parent.top)
                }
        )

        IconButton(
            onClick = { navController?.navigate(Routes.adminMain) },
            modifier = Modifier
                .constrainAs(adminButton) {
                    end.linkTo(exitButton.start)
                    centerVerticallyTo(title)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_admin),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = sovcombankRed
            )
        }

        IconButton(
            onClick = { navController?.navigate(Routes.services) },
            modifier = Modifier
                .constrainAs(exitButton) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(title)
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_exit),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = sovcombankRed
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo_sovcombank),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Якубович Леонид Аркадьевич\n\n77 лет",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 20.dp)
        )
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_email),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 50.dp,)
        )

        Text(
            text = "evdanilov_2@edu.hse.ru",
            style = MaterialTheme.typography.bodyMedium.merge(SpanStyle(textDecoration = TextDecoration.Underline)),
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 5.dp)
        )

        Text(
            text = stringResource(R.string.profile_phone_number),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "+7000000000",
            style = MaterialTheme.typography.bodyMedium.merge(SpanStyle(textDecoration = TextDecoration.Underline)),
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 5.dp)
        )

        Text(
            text = stringResource(R.string.profile_password),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "***********",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(top = 5.dp,)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ProfileScreenPreview() = SovcombankChallengeTheme {
    ProfileScreenContent()
}
package io.lostimagin4tion.sovcombankchallenge.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(4000)
        navController.popBackStack()
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() = ConstraintLayout(
    modifier = Modifier.fillMaxSize()
) {
    val (logo, loader, funnyPhrase) = createRefs()

    Image(
        painter = painterResource(
            if (isSystemInDarkTheme()) R.drawable.logo_sovcombank_ru_white
            else R.drawable.logo_sovcombank_ru
        ),
        contentDescription = null,
        modifier = Modifier
            .constrainAs(logo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
            .padding(horizontal = 30.dp)
            .padding(top = 150.dp)
    )

    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .constrainAs(loader) {
                centerHorizontallyTo(parent)
                bottom.linkTo(funnyPhrase.top)
            }
            .padding(bottom = 50.dp)
            .size(48.dp)
    )

    Text(
        text = stringResource(R.string.funny_phrase_sample),
        color = MaterialTheme.colorScheme.onSecondary,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier
            .constrainAs(funnyPhrase) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(horizontal = 20.dp)
            .padding(bottom = 100.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun AuthorizationScreenPreview() = SovcombankChallengeTheme {
    SplashScreenContent()
}
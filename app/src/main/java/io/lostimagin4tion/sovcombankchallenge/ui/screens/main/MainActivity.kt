package io.lostimagin4tion.sovcombankchallenge.ui.screens.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.state.ThemeSetting
import io.lostimagin4tion.sovcombankchallenge.ui.screens.passwordrecovery.PasswordRecoveryScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.accountdetails.AccountDetailsScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.accountinfo.AccountInfoScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.authorization.AuthorizationScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.codeconfirmation.CodeConfirmationScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.accountedit.EditAccountScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.admin.adminaccount.AdminAccountScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.admin.adminmain.AdminMainScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.admin.adminusers.AdminUsersScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.home.HomeScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.newAccount.NewAccountScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.news.NewsScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.operationdetails.OperationDetailsScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.operationhistory.OperationHistoryScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.profile.ProfileScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.registration.RegistrationScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.services.ServicesScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.splash.SplashScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.topup.TopUpScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.trading.TradingScreen
import io.lostimagin4tion.sovcombankchallenge.ui.screens.transfer.TransferScreen
import io.lostimagin4tion.sovcombankchallenge.ui.theme.SovcombankChallengeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            val viewModel: MainViewModel = viewModel()
            val theme by viewModel.theme.collectAsState()

            val darkTheme = when (theme) {
                ThemeSetting.Light -> false
                ThemeSetting.Dark -> true
                ThemeSetting.System -> isSystemInDarkTheme()
            }

            SovcombankChallengeTheme(darkTheme) {
                DisposableEffect(systemUiController) {
                    systemUiController.let {
                        it.setStatusBarColor(
                            Color.Transparent
                        )
                        it.setNavigationBarColor(
                            Color.Transparent
                        )
                    }

                    onDispose {}
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        SnackbarHost(
                            hostState = it,
                            modifier = Modifier.navigationBarsPadding()
                        ) {
                            Snackbar(
                                snackbarData = it,
                                backgroundColor = MaterialTheme.colorScheme.primary,
                                contentColor = contentColorFor(Color.Black),
                                shape = MaterialTheme.shapes.small
                            )
                        }
                    },
                    bottomBar = {
                        val items = Screens.values()
                        val routes = items.map { it.route }
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute =
                            navBackStackEntry?.destination?.hierarchy?.first()?.route

                        // hide bottom bar for other screens
                        if (currentRoute !in routes) return@Scaffold

                        NavigationBar(
                            modifier = Modifier.height(75.dp)
                        ) {
                            items.forEach { screen ->
                                NavigationBarItem(
                                    modifier = Modifier
                                        .navigationBarsPadding()
                                        .clip(CircleShape),
                                    icon = {
                                        Icon(
                                            painter = painterResource(screen.iconId),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(24.dp),
                                            tint = MaterialTheme.colorScheme.primaryContainer
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = stringResource(screen.resourceId),
                                            color = MaterialTheme.colorScheme.primaryContainer
                                        )
                                    },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        if (screen.route != currentRoute) {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) { }
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    },
                    content = {
                        MainScreen(
                            viewModel = viewModel,
                            scaffoldState = scaffoldState,
                            paddingValues = it,
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}

enum class Screens(val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int) {
    Home(Routes.home, R.string.home, R.drawable.ic_home),
    Trading(Routes.trading, R.string.trading, R.drawable.ic_trading),
    News(Routes.news, R.string.news, R.drawable.ic_news),
}

object Routes {
    const val splash = "splash"
    const val authorization = "authorization"
    const val registration = "registration"
    const val codeConfirmation = "codeConfirmation"
    const val passwordRecovery = "passwordRecovery"
    const val newAccount = "newAccount"
    const val operationDetails = "operationDetails"
    const val accountDetails = "accountDetails"
    const val operationHistory = "operationHistory"
    const val services = "services"
    const val transfer = "transfer"
    const val topUp = "topUp"
    const val editAccount = "editAccount"
    const val accountInfo = "accountInfo"
    const val profile = "profile"
    const val adminMain = "adminMain"
    const val adminAccounts = "adminAccounts"
    const val adminUsers = "adminUsers"

    const val home = "home"
    const val trading = "trading"
    const val news = "news"
}

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showMessage: (Int) -> Unit = { message ->
        val strMessage = context.getString(message)
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(strMessage)
        }
    }
    val showTextMessage: (String) -> Unit = { message ->
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    val isLogged by viewModel.isLogged.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.splash
        ) {
            composable(Routes.splash) {
                SplashScreen(navController = navController)

                LaunchedEffect(Unit) {
                    delay(2000)

                    if (isLogged) {
                        navController.navigate(Routes.home)
                    }
                    else {
                        navController.navigate(Routes.authorization)
                    }
                }
            }

            composable(Routes.home) {
                HomeScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.trading) {
                TradingScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.news) {
                NewsScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.profile) {
                ProfileScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.authorization) {
                AuthorizationScreen(
                    navController = navController,
                    showMessage = showMessage,
                    showTextMessage = showTextMessage
                )
            }

            composable(Routes.registration) {
                RegistrationScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.codeConfirmation) {
                CodeConfirmationScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.passwordRecovery) {
                PasswordRecoveryScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.newAccount) {
                NewAccountScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.operationDetails) {
                OperationDetailsScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.accountDetails) {
                AccountDetailsScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.operationHistory) {
                OperationHistoryScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.services) {
                ServicesScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.transfer) {
                TransferScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.topUp) {
                TopUpScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.editAccount) {
                EditAccountScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.accountInfo) {
                AccountInfoScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.adminMain) {
                AdminMainScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.adminUsers) {
                AdminUsersScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }

            composable(Routes.adminAccounts) {
                AdminAccountScreen(
                    navController = navController,
                    showMessage = showMessage
                )
            }
        }
    }
}
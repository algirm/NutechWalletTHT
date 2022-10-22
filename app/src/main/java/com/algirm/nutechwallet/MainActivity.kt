package com.algirm.nutechwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.algirm.nutechwallet.core.domain.preferences.Preferences
import com.algirm.nutechwallet.feature_ewallet.presentation.ewallet.EwalletScreen
import com.algirm.nutechwallet.feature_ewallet.presentation.ewallet.MainViewModel
import com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list.AccountListScreen
import com.algirm.nutechwallet.feature_ewallet.presentation.transfer.transfer_amount.TransferScreen
import com.algirm.nutechwallet.feature_login.presentation.LoginScreen
import com.algirm.nutechwallet.feature_registration.presentation.RegisterScreen
import com.algirm.nutechwallet.navigation.Route
import com.algirm.nutechwallet.ui.theme.NutechWalletTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isLogin = preferences.loadIsLogin()
        setContent {
            NutechWalletTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val mainViewModel: MainViewModel = hiltViewModel()

//                val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
//                    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
//                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (isLogin) Route.WALLET_OVERVIEW else Route.LOGIN
                    ) {
                        composable(Route.LOGIN) {
                            LoginScreen(
                                onLoginClick = {
                                    navController.navigate(Route.WALLET_OVERVIEW) {
                                        popUpTo(Route.LOGIN) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate(Route.REGISTER) {
                                        popUpTo(Route.REGISTER) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                scaffoldState = scaffoldState
//                                viewModel = hiltViewModel<LoginViewModel>(viewModelStoreOwner = viewModelStoreOwner)
                            )
                        }
                        composable(Route.REGISTER) {
//                            CompositionLocalProvider(
//                                LocalViewModelStoreOwner provides viewModelStoreOwner
//                            ) {
//
//                            }
                            RegisterScreen(
                                onNavigateUp = {
                                    navController.navigateUp()
//                                    navController.navigate(Route.LOGIN) {
//                                        popUpTo(Route.LOGIN) {
//                                            saveState = true
//                                        }
//                                        launchSingleTop = true
////                                        restoreState = true
//                                    }
                                },
                                onBackPressed = {
                                    navController.navigate(Route.LOGIN) {
                                        popUpTo(Route.LOGIN) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
//                                        restoreState = true
                                    }
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.WALLET_OVERVIEW) {
                            EwalletScreen(
                                onTransferClick = {
                                    navController.navigate(Route.TRANSFER) {
                                        popUpTo(Route.TRANSFER) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.TRANSFER) {
                            AccountListScreen(
                                onUserClick = { userId, userName ->
                                    navController.navigate(
                                        Route.TRANSFER + "/$userId/$userName"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.TRANSFER + "/{userId}/{userName}",
                            arguments = listOf(
                                navArgument("userId") { type = NavType.IntType },
                                navArgument("userName") { type = NavType.StringType },
                            )
                        ) {
                            val userId = it.arguments?.getInt("userId")!!
                            val userName = it.arguments?.getString("userName")!!
                            TransferScreen(
                                userId = userId,
                                userName = userName,
                                scaffoldState = scaffoldState,
                                onSuccess = {
                                    navController.navigate(Route.WALLET_OVERVIEW) {
                                        popUpTo(Route.WALLET_OVERVIEW) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


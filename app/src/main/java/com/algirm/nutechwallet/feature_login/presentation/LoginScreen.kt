package com.algirm.nutechwallet.feature_login.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core_ui.LocalSpacing
import com.algirm.nutechwallet.ui.components.EmailInput
import com.algirm.nutechwallet.ui.components.PasswordInput

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Success -> onLoginClick()
                is UiEvent.NavigateUp -> Unit
            }
        }
    }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailInput(
                modifier = Modifier.fillMaxWidth(),
                email = state.textEmail,
                onEmailChanged = { viewModel.onEvent(LoginEvent.EmailChanged(it)) }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            PasswordInput(
                modifier = Modifier.fillMaxWidth(),
                password = state.textPassword,
                onPasswordChanged = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black),
                onClick = { viewModel.onEvent(LoginEvent.Login) },
//                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black),
                onClick = onRegisterClick,
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }
        }
    }
}
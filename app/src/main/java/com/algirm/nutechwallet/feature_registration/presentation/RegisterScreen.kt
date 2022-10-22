package com.algirm.nutechwallet.feature_registration.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.algirm.nutechwallet.core.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

//data class TextFieldValue(val email: String)
//
//val textSaver = run {
//    mapSaver(
//        save = { mapOf("textEmail" to it.email) },
//        restore = { TextFieldValue(it["textEmail"] as String) }
//    )
//}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RegisterScreen(
    onBackPressed: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState
) {
//    val viewModel: RegisterViewModel = hiltViewModel()

//    val state by viewModel.state.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

//    var textEmail by rememberSaveable { mutableStateOf("") }
//    var textEmail by rememberSaveable { mutableStateOf(TextFieldValue(state.textEmail)) }
//    var textEmail by rememberSaveable(stateSaver = textSaver) { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    BackHandler(onBack = onBackPressed)
    RegisterContent(
        onRegisterClick = onNavigateUp,
        state = state,
        onEvent = viewModel::onEvent
    )

    /*Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            Text(
                text = "Sign Up for an account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
            )
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //            TextField(
                //                value = textEmail,
                //                modifier = Modifier.fillMaxWidth(),
                //                onValueChange = { textEmail = it },
                //                placeholder = { Text(text = "Email") }
                //            )
                //            TextField(
                //                value = textEmail.email,
                //                modifier = Modifier.fillMaxWidth(),
                //                onValueChange = { textEmail = TextFieldValue(email = it) },
                //                placeholder = { Text(text = "Email") }
                //            )
                TextField(
                    value = state.textEmail,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
                    placeholder = { Text(text = "Email") }
                )
                TextField(
                    value = state.textPassword,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {},
                    placeholder = { Text(text = "Password") }
                )
                Button(
                    onClick = { onRegisterClick() }
                ) {
                    Text(text = "Register")
                }
            }
        }
    }*/
}
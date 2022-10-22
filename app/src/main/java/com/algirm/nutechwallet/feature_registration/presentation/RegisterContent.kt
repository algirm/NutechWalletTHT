package com.algirm.nutechwallet.feature_registration.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.algirm.nutechwallet.core_ui.LocalSpacing
import com.algirm.nutechwallet.ui.components.EmailInput
import com.algirm.nutechwallet.ui.components.PasswordInput

@Composable
fun RegisterContent(
    onRegisterClick: () -> Unit,
    state: RegisterState,
    modifier: Modifier = Modifier,
    onEvent: (RegisterEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    var isPasswordHidden by remember {
        mutableStateOf(true)
    }
    Box(
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
                OutlinedTextField(
                    value = state.textFirstName,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { onEvent(RegisterEvent.FirstNameChanged(it)) },
                    label = { Text(text = "First Name") },
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )
                Spacer(modifier = modifier.height(spacing.spaceMedium))
                OutlinedTextField(
                    value = state.textLastName,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { onEvent(RegisterEvent.LastNameChanged(it)) },
                    label = { Text(text = "Last Name") },
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )
                Spacer(modifier = modifier.height(spacing.spaceMedium))
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = state.textEmail,
                    onEmailChanged = { onEvent(RegisterEvent.EmailChanged(it)) }
                )
                /*OutlinedTextField(
                    value = state.textEmail,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { onEvent(RegisterEvent.EmailChanged(it)) },
                    singleLine = true,
                    label = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    shape = RoundedCornerShape(50)
                )*/
                Spacer(modifier = modifier.height(spacing.spaceMedium))
                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    password = state.textPassword,
                    onPasswordChanged = { onEvent(RegisterEvent.PasswordChanged(it)) }
                )
                /*OutlinedTextField(
                    value = state.textPassword,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { onEvent(RegisterEvent.PasswordChanged(it)) },
                    singleLine = true,
                    label = { Text(text = "Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isPasswordHidden = !isPasswordHidden
                            }
                        ) {
                            Icon(
                                imageVector = if (isPasswordHidden) {
                                    Icons.Default.Visibility
                                } else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                    ),
                    visualTransformation = if (isPasswordHidden) {
                        PasswordVisualTransformation()
                    } else VisualTransformation.None,
                    shape = RoundedCornerShape(50)
                )*/
                Spacer(modifier = modifier.height(spacing.spaceMedium))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black),
                    onClick = { onEvent(RegisterEvent.Register) },
                    colors = ButtonDefaults.buttonColors(
//                        contentColor = MaterialTheme.colors.secondary,
//                        backgroundColor = MaterialTheme.colors.primary
                    )
                ) {
                    Text(text = "Register", fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewApp() {
    RegisterContent(state = RegisterState(), onEvent = {}, onRegisterClick = {})
}
package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.transfer_amount

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.algirm.nutechwallet.core.util.UiEvent
import com.algirm.nutechwallet.core_ui.LocalSpacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TransferScreen(
    onSuccess: () -> Unit,
    userId: Int,
    userName: String,
    viewModel: TransferViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                UiEvent.NavigateUp -> Unit
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message.asString(context))
                }
                UiEvent.Success -> onSuccess()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceMedium)
    ) {
        Text(
            text = "Transfer",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
        Divider()
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
            Text(text = userName)
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceLarge))
        Divider()
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
        Text(
            text = "Transfer Amount :",
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.amount,
            onValueChange = { viewModel.onEvent(TransferEvent.OnAmountChanged(it)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            shape = RoundedCornerShape(10)
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceLarge))
        Button(
            onClick = { viewModel.onEvent(TransferEvent.OnTransfer(state.amount, userId)) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Transfer", fontSize = 18.sp)
        }
    }
}
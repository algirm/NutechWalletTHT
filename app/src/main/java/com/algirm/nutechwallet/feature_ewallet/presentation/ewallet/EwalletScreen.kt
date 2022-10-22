package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.algirm.nutechwallet.core_ui.LocalSpacing
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list.UserItem

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EwalletScreen(
    onTransferClick: () -> Unit,
    viewModel: EwalletViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = scaffoldState) {
        viewModel.uiEvent
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
        ) {
            Row(
                modifier = Modifier
                    .padding(LocalSpacing.current.spaceMedium)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = { },
                    modifier= Modifier.size(48.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(LocalSpacing.current.spaceMedium)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(25)
                    )
                    .padding(LocalSpacing.current.spaceMedium)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BalanceInfo(
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.onPrimary,
                            shape = RoundedCornerShape(25)
                        ),
                        balanceAmount = state.balance
                    )
                    Button(
                        onClick = {viewModel.onEvent(EwalletEvent.TopUp(10000))}
                    ) {
                        Text(text = "Top Up")
                    }
                    Button(
                        onClick = onTransferClick
                    ) {
                        Text(text = "Transfer")
                    }
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
                    Text(
                        text = "Transaction History",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.transactionHistory.size) { index ->
                            val transaction = state.transactionHistory[index]
                            if (index > 0) {
                                Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
                            }

                            TransactionHistoryItem(transaction = transaction)

                            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
                            if (index < state.transactionHistory.size - 1) Divider()
                        }
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
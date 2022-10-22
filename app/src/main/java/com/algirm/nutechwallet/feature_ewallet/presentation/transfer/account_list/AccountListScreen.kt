package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.algirm.nutechwallet.core_ui.LocalSpacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AccountListScreen(
    onUserClick: (Int, String) -> Unit,
    viewModel: AccountListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.spaceMedium)
        ) {
            Button(onClick = { viewModel.onEvent(AccountListEvent.OnAddUserClick) }) {
                Text(text = "Add User")
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.userList.size) { index ->
                    val user = state.userList[index]
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
                    }
                    UserItem(
                        onClick = onUserClick,
                        user = user
                    )
                    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
                    if (index < state.userList.size - 1) Divider()
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
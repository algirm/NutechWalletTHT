package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.algirm.nutechwallet.core_ui.LocalSpacing

@Composable
fun BalanceInfo(
    modifier: Modifier = Modifier,
    balanceAmount: Int?
) {
    Box(
        modifier = modifier.padding(LocalSpacing.current.spaceSmall)
    ) {
        Column {
            Text(text = "My Balance")
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            Text(text = balanceAmount.toString())
        }
    }
}
package com.algirm.nutechwallet.feature_ewallet.presentation.ewallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.algirm.nutechwallet.core_ui.LocalSpacing
import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionDto

@Composable
fun TransactionHistoryItem(
    transaction: TransactionDto
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = transaction.transaction_type.replaceFirstChar { it.uppercaseChar() })
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
                Text(text = transaction.amount.toString())
                Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = transaction.transaction_time)
            }
        }
    }
}
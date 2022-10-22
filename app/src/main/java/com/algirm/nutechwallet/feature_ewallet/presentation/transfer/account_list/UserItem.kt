package com.algirm.nutechwallet.feature_ewallet.presentation.transfer.account_list

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
import com.algirm.nutechwallet.core_ui.LocalSpacing
import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User

@Composable
fun UserItem(
    onClick: (Int, String) -> Unit,
    user: User
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick(user.id!!, user.name!!) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { onClick(user.id!!, user.name!!) },
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
            Text(text = user.name ?: "-")
        }
    }
}
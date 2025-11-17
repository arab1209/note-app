package com.example.toyproject_note.presentation.memodetail.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShareButton(
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onShareClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "공유하기",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
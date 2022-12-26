package com.splanes.gifting.ui.common.components.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.common.utils.color.colorOf

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    state: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    modalContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = state,
        modifier = modifier,
        sheetContent = modalContent,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorOf { surface },
        sheetContentColor = colorOf { onSurface },
        content = content
    )
}

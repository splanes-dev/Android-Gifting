package com.splanes.gifting.ui.common.components.spacer.column

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer as ComposeSpacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Spacer(height: Dp) {
    ComposeSpacer(modifier = Modifier.height(height))
}

@Composable
fun ColumnScope.Weight(weight: Double = 1.0) {
    ComposeSpacer(modifier = Modifier.weight(weight.toFloat()))
}

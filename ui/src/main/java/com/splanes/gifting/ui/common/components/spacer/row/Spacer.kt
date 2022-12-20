package com.splanes.gifting.ui.common.components.spacer.row

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer as ComposeSpacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.Spacer(width: Dp) {
    ComposeSpacer(modifier = Modifier.width(width))
}

@Composable
fun RowScope.Weight(weight: Double = 1.0) {
    ComposeSpacer(modifier = Modifier.weight(weight.toFloat()))
}

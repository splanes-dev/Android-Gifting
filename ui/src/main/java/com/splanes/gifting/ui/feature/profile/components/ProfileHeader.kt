package com.splanes.gifting.ui.feature.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Draw
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.ui.common.components.buttons.GiftingIconButton
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.typography.textStyleOf

@Composable
fun ProfileHeader(
    profile: Profile,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = profile.avatarUrl)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = colorOf { onSurface.withAlpha(.1) }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(75.dp).clip(RoundedCornerShape(8.dp)),
                painter = painter,
                contentDescription = profile.name
            )

            Spacer(width = 16.dp)

            Column {
                Text(text = profile.name, style = textStyleOf { headlineMedium })

                Spacer(height = 4.dp)

                Text(
                    text = profile.email,
                    style = textStyleOf { bodyLarge },
                    color = colorOf { onSurface.withAlpha(.6) }
                )
            }

            Weight()

            GiftingIconButton(
                imageVector = Icons.Rounded.Draw,
                tint = colorOf { onSurface.withAlpha(.65) },
                onClick = onEditClick
            )
        }
    }
}

package io.lostimagin4tion.sovcombankchallenge.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * [TextElevatedButton] - basic Material You filled button
 * Contains Text and optional Icon
 *
 * @author Egor Danilov
 */
@Composable
fun TextElevatedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: ButtonColors,
    contentPadding: PaddingValues,
    @StringRes textId: Int,
    @DrawableRes iconId: Int? = null,
    isEnabled: Boolean = true
) {
    ElevatedButton(
        onClick = onClick,
        enabled = isEnabled,
        contentPadding = contentPadding,
        colors = colors,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 6.dp
        ),
        modifier = modifier
    ) {
        iconId?.let {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = stringResource(textId),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.iconPadding(iconId)
        )
    }
}
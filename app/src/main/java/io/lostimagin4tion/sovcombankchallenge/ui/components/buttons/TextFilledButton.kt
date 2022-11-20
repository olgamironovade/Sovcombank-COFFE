package io.lostimagin4tion.sovcombankchallenge.ui.components.buttons


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
 * [TextFilledButton] - basic Material You filled button
 * Contains Text and optional Icon
 *
 * @author Egor Danilov
 */
@Composable
fun TextFilledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: ButtonColors,
    @StringRes textId: Int,
    @DrawableRes iconId: Int? = null,
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        contentPadding = PaddingValues(vertical = 15.dp),
        colors = colors,
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
            modifier = Modifier.iconPadding(iconId)
        )
    }
}

fun Modifier.iconPadding(iconResource: Int?): Modifier =
    if (iconResource != null) then(this.padding(start = 10.dp)) else this

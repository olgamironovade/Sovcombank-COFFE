package io.lostimagin4tion.sovcombankchallenge.ui.components.dividers

import androidx.compose.ui.unit.dp
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * [Divider] - divider for dividing visually some elements, e.g. buttons
 * Looks like two horizontal lines with Text between
 *
 * @author Egor Danilov
 */
@Composable
fun Divider(
    textModifier: Modifier,
    paddingModifier: Modifier = Modifier,
    @StringRes textResource: Int
) = Row(
    modifier = paddingModifier
) {
    DivideLine()

    Text(
        text = stringResource(textResource),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = textModifier
    )

    DivideLine()
}

@Composable
private fun RowScope.DivideLine() = Surface(
    color = MaterialTheme.colorScheme.primary,
    modifier = Modifier
        .size(
            width = 75.dp,
            height = 1.dp
        )
        .align(Alignment.CenterVertically),
    content = {}
)

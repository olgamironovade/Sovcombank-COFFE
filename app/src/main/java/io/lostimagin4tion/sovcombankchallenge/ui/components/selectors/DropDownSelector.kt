package io.lostimagin4tion.sovcombankchallenge.ui.components.selectors

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankGray

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DropDownSelector(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    @StringRes labelId: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    val textStyle = MaterialTheme.typography.bodySmall
    val focusManager = LocalFocusManager.current
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle = textStyle,
            singleLine = true,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text= stringResource(labelId), style = textStyle) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onSecondary,
                cursorColor = MaterialTheme.colorScheme.onSecondary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = sovcombankGray,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorTrailingIconColor = MaterialTheme.colorScheme.error,
                focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                unfocusedLabelColor = sovcombankGray,
                errorLabelColor = MaterialTheme.colorScheme.error,
                placeholderColor = sovcombankGray,
                focusedLeadingIconColor = MaterialTheme.colorScheme.tertiary,
                unfocusedLeadingIconColor = sovcombankGray,
                focusedTrailingIconColor = MaterialTheme.colorScheme.tertiary,
                unfocusedTrailingIconColor = sovcombankGray
            ),
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { isExpanded = !isExpanded })
            }
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) { textFieldSize.width.toDp() }
                )
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(TextFieldValue(label))
                        isExpanded = false
                    }
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}
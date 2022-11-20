package io.lostimagin4tion.sovcombankchallenge.ui.components.textfields

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankGray
import io.lostimagin4tion.sovcombankchallenge.ui.theme.sovcombankLightBlue

/**
 * [CommonTextField] - Text Field for [AuthorizationScreen]
 *
 * @author Egor Danilov
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CommonTextField(
    value: TextFieldValue,
    @StringRes labelId: Int,
    @DrawableRes leadingIconId: Int? = null,
    @DrawableRes trailingIconId: Int? = null,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    val textStyle = MaterialTheme.typography.bodySmall

    var trailingIcon: @Composable (() -> Unit)? = null
    var leadingIcon: @Composable (() -> Unit)? = null

    var isPasswordVisible by remember { mutableStateOf(false) }

    trailingIconId?.let {
        trailingIcon = @Composable {
            AnimatedVisibility(
                visible = value.text.isNotEmpty(),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                IconButton(onClick = { onValueChange(TextFieldValue()) }) {
                    Icon(
                        painter = painterResource(trailingIconId),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = sovcombankGray
                    )
                }
            }
        }
    }

    leadingIconId?.let {
        leadingIcon = @Composable {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = sovcombankGray
            )
        }
    }

    if (keyboardType == KeyboardType.Password) {
        leadingIcon = @Composable {
            IconButton(onClick = {
                isPasswordVisible = !isPasswordVisible
            }) {
                Icon(
                    imageVector = if (isPasswordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = null
                )
            }
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = true,
        label = { Text(text = stringResource(labelId), style = textStyle) },
        visualTransformation =
            if (isPasswordVisible || visualTransformation == VisualTransformation.None)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        isError = isError,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.surfaceVariant,
            cursorColor = MaterialTheme.colorScheme.surfaceVariant,
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
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
    )
}

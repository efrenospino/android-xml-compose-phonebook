package co.wawand.mobile.phonebook.compose.ui.feat.contacts

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.wawand.mobile.phonebook.base.R

@Composable
fun ContactSearchInput(
    modifier: Modifier,
    input: String,
    onInputChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = input,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            focusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        label = {
            Text(
                text = stringResource(id = R.string.search),
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.type_something),
            )
        },
        onValueChange = onInputChange,
        trailingIcon = {
            if (input.isNotEmpty()) {
                IconButton(onClick = {
                    onInputChange("")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_clear_24),
                        contentDescription = stringResource(
                            id = R.string.clear
                        )
                    )
                }
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.outline_search_24),
                contentDescription = stringResource(
                    id = R.string.search
                )
            )
        }
    )
}
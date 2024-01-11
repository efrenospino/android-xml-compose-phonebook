package co.wawand.mobile.phonebook.compose.ui.feat.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.wawand.mobile.phonebook.base.R
import co.wawand.mobile.phonebook.compose.ui.theme.PhonebookTheme
import co.wawand.mobile.phonebook.compose.ui.theme.gray
import co.wawand.mobile.phonebook.compose.ui.theme.white
import co.wawand.mobile.phonebook.data.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(viewModel: ContactListViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
        )
    }) { innerPadding ->
        ContactListLayout(uiState = uiState, modifier = Modifier.padding(innerPadding)) {
            viewModel.searchContacts(it)
        }
    }
}

@Composable
private fun ContactListLayout(
    uiState: UiState,
    modifier: Modifier = Modifier,
    onInputChange: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        ContactSearchInput(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            input = uiState.input,
            onInputChange = onInputChange,
        )
        ContactList(uiState = uiState)
    }
}

@Composable
private fun ContactSearchInput(
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

@Composable
private fun ContactList(uiState: UiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(uiState.contactsGroupList) { item ->
            when (item) {
                is PhonebookItem.AlphabeticalSection -> Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                )

                is PhonebookItem.Contact -> ContactCard(item.contact)
            }
        }
    }
}

@Composable
private fun ContactCard(contact: Contact) {
    ElevatedCard(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = white)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = gray,
                            radius = this.size.maxDimension
                        )
                    },
                text = contact.firstLetter,
                style = MaterialTheme.typography.bodyLarge,
                color = white
            )
            Column {
                Text(
                    text = contact.fullName,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = contact.phoneNumber,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactListLayoutPreview() {
    PhonebookTheme {
        ContactListLayout(
            uiState = UiState(
                listOf(
                    PhonebookItem.AlphabeticalSection("E"),
                    PhonebookItem.Contact(
                        Contact(
                            firstName = "Elon",
                            lastName = "Musk",
                            phoneNumber = "+112313213"
                        )
                    ),
                    PhonebookItem.AlphabeticalSection("S"),
                    PhonebookItem.Contact(
                        Contact(
                            firstName = "Steve",
                            lastName = "Jobs",
                            phoneNumber = "+112313213"
                        )
                    )
                )
            )
        )
    }
}

package co.wawand.mobile.phonebook.compose.ui.feat.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.wawand.mobile.phonebook.base.R
import co.wawand.mobile.phonebook.compose.ui.theme.PhonebookTheme
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
    uiState: UiState, modifier: Modifier = Modifier, onInputChange: (String) -> Unit = {}
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
fun ContactList(uiState: UiState) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactListLayoutPreview() {
    PhonebookTheme {
        ContactListLayout(
            uiState = UiState(
                listOf(
                    PhonebookItem.AlphabeticalSection("E"), PhonebookItem.Contact(
                        Contact(
                            firstName = "Elon", lastName = "Musk", phoneNumber = "+112313213"
                        )
                    ), PhonebookItem.AlphabeticalSection("S"), PhonebookItem.Contact(
                        Contact(
                            firstName = "Steve", lastName = "Jobs", phoneNumber = "+112313213"
                        )
                    )
                )
            )
        )
    }
}

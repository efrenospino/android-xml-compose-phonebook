package co.wawand.mobile.phonebook.compose.ui.feat.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.wawand.mobile.phonebook.data.ext.flat
import co.wawand.mobile.phonebook.data.repositories.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface PhonebookItem {
    data class AlphabeticalSection(val title: String) : PhonebookItem
    data class Contact(val contact: co.wawand.mobile.phonebook.data.model.Contact) : PhonebookItem
}

data class UiState(
    val contactsGroupList: List<PhonebookItem> = emptyList(),
    val input: String = ""
)

class ContactListViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private val viewModelState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState = viewModelState.asStateFlow()

    init {
        searchContacts()
    }

    fun searchContacts(input: String = "") {
        viewModelScope.launch {
            viewModelState.update { uiState ->
                uiState.copy(
                    input = input,
                    contactsGroupList = contactsRepository.getContactsGroupList(input).flat(
                        transformSection = { PhonebookItem.AlphabeticalSection(it) },
                        transformContact = { PhonebookItem.Contact(it) },
                    )
                )
            }
        }
    }

}

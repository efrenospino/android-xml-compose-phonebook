package co.wawand.mobile.phonebook.mdc.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import co.wawand.mobile.phonebook.data.model.ContactsGroup
import co.wawand.mobile.phonebook.data.repositories.ContactsRepository

class ContactListViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private val contactListLiveData = MutableLiveData<List<ContactsGroup>>()
    val contactList: LiveData<List<ContactsGroup>> = contactListLiveData

    init {
        searchContacts("")
    }

    fun searchContacts(input: String) {
        contactsRepository.getContactsGroupList(input).let {
            contactListLiveData.value = it
        }

    }

    companion object {

        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[APPLICATION_KEY])
                val contactsRepository =
                    (application as co.wawand.mobile.phonebook.base.PhonebookApplication).appProvider.contactsRepository

                return ContactListViewModel(contactsRepository) as T
            }
        }
    }

}

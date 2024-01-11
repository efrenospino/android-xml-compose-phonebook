package co.wawand.mobile.phonebook.data

import co.wawand.mobile.phonebook.data.repositories.ContactsRepository

interface RepositoriesProvider {
    val contactsRepository: ContactsRepository
}

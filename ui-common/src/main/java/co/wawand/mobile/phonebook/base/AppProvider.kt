package co.wawand.mobile.phonebook.base

import co.wawand.mobile.phonebook.data.RepositoriesProvider
import co.wawand.mobile.phonebook.data.lazy.LazyContactsRepository
import co.wawand.mobile.phonebook.data.lazy.LazyDataSource
import co.wawand.mobile.phonebook.data.repositories.ContactsRepository

class AppProvider : RepositoriesProvider {

    override val contactsRepository: ContactsRepository by lazy {
        LazyContactsRepository(dataSource = LazyDataSource)
    }

}

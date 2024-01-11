package co.wawand.mobile.phonebook.data.repositories

import co.wawand.mobile.phonebook.data.model.ContactsGroup

interface ContactsRepository {

    fun getContactsGroupList(search: String): List<ContactsGroup>

}


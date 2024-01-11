package co.wawand.mobile.phonebook.data.lazy

import co.wawand.mobile.phonebook.data.model.ContactsGroup
import co.wawand.mobile.phonebook.data.repositories.ContactsRepository

class LazyContactsRepository(private val dataSource: LazyDataSource) : ContactsRepository {

    override fun getContactsGroupList(search: String): List<ContactsGroup> {
        return dataSource.contactList.filter {
            it.fullName.contains(search, ignoreCase = true)
        }.groupBy {
            it.firstLetter
        }.toSortedMap().map { entry ->
            ContactsGroup(
                group = entry.key,
                contacts = entry.value.sortedBy {
                    it.fullName
                }
            )
        }
    }

}

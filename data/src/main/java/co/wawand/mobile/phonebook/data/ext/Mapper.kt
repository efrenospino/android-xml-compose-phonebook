package co.wawand.mobile.phonebook.data.ext

import co.wawand.mobile.phonebook.data.model.Contact
import co.wawand.mobile.phonebook.data.model.ContactsGroup

fun <T> List<ContactsGroup>.flat(
    transformSection: (String) -> T,
    transformContact: (Contact) -> T
): List<T> {

    val flatContactList = mutableListOf<T>()

    forEach { (section, contacts) ->
        flatContactList.add(transformSection(section))
        flatContactList.addAll(contacts.map { contact ->
            transformContact(contact)
        })
    }

    return flatContactList
}


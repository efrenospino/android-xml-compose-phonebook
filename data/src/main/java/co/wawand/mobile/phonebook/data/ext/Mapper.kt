package co.wawand.mobile.phonebook.data.ext

import co.wawand.mobile.phonebook.data.model.Contact
import co.wawand.mobile.phonebook.data.model.ContactsGroup
import java.util.UUID

fun <T> List<ContactsGroup>.flatWithId(
    transformSection: (UUID, String) -> T,
    transformContact: (UUID, Contact) -> T
): List<T> {

    val flatContactList = mutableListOf<T>()

    forEach { (section, contacts) ->
        flatContactList.add(transformSection(UUID.randomUUID(), section))
        flatContactList.addAll(contacts.map { contact ->
            transformContact(UUID.randomUUID(), contact)
        })
    }

    return flatContactList
}

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


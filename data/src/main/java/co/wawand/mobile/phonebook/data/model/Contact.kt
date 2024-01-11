package co.wawand.mobile.phonebook.data.model

import java.util.UUID

data class Contact(
    val id: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
) {

    val fullName: String
        get() = "$firstName $lastName"

    val firstLetter: String
        get() = firstName.first().toString()
}

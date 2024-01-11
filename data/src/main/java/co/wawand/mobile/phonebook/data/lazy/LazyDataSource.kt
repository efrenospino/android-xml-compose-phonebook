package co.wawand.mobile.phonebook.data.lazy

import co.wawand.mobile.phonebook.data.model.Contact

object LazyDataSource {

    val contactList: List<Contact> = generateCelebrityContactList()

    private fun generateCelebrityContactList(): List<Contact> {
        val contactList = mutableListOf<Contact>()

        repeat(100) {
            val randomFirstName = generateRandomFirstName()
            val randomLastName = generateRandomLastName()
            val randomPhoneNumber = generateRandomPhoneNumber()

            val contact = Contact(
                firstName = randomFirstName,
                lastName = randomLastName,
                phoneNumber = randomPhoneNumber
            )

            contactList.add(contact)
        }

        return contactList
    }

    private fun generateRandomFirstName(): String {
        val firstNames = listOf(
            "Tom",
            "Jennifer",
            "Chris",
            "Emma",
            "Leonardo",
            "Gal",
            "Dwayne",
            "Angelina",
            "Ryan",
            "Scarlett",
            "Brad",
            "Natalie",
            "Robert",
            "Charlize",
            "Johnny",
            "Margot",
        )
        return firstNames.random()
    }

    private fun generateRandomLastName(): String {
        val lastNames = listOf(
            "Hanks",
            "Lawrence",
            "Hemsworth",
            "Watson",
            "DiCaprio",
            "Gadot",
            "Johnson",
            "Jolie",
            "Reynolds",
            "Johansson",
            "Pitt",
            "Portman",
            "Downey Jr.",
            "Theron",
            "Depp",
            "Robbie",
        )
        return lastNames.random()
    }

    private fun generateRandomPhoneNumber(): String {
        val sb = StringBuilder("+1") // Assuming US phone numbers for simplicity

        repeat(10) {
            sb.append((0..9).random())
        }

        return sb.toString()
    }
}

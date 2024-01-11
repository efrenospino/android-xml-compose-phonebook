package co.wawand.mobile.phonebook.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.wawand.mobile.phonebook.compose.ui.feat.contacts.ContactListScreen
import co.wawand.mobile.phonebook.compose.ui.feat.contacts.ContactListViewModel

@Composable
fun PhonebookNavGraph(
    appProvider: co.wawand.mobile.phonebook.base.AppProvider,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController, startDestination = "contact-list"
    ) {
        composable(route = "contact-list") {
            ContactListScreen(
                viewModel = ContactListViewModel(appProvider.contactsRepository),
            )
        }
    }
}

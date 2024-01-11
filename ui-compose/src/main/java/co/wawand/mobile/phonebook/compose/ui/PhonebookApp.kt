package co.wawand.mobile.phonebook.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import co.wawand.mobile.phonebook.compose.ui.navigation.PhonebookNavGraph
import co.wawand.mobile.phonebook.compose.ui.theme.PhonebookTheme

@Composable
fun PhonebookApp(appProvider: co.wawand.mobile.phonebook.base.AppProvider) {
    PhonebookTheme {
        PhonebookNavGraph(
            appProvider = appProvider,
            navHostController = rememberNavController(),
        )
    }
}

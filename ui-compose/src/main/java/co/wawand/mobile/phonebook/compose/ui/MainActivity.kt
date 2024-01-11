package co.wawand.mobile.phonebook.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    private val appProvider: co.wawand.mobile.phonebook.base.AppProvider
        get() = (application as co.wawand.mobile.phonebook.base.PhonebookApplication).appProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhonebookApp(appProvider)
        }
    }
}

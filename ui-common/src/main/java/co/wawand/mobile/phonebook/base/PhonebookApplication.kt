package co.wawand.mobile.phonebook.base

import android.app.Application

class PhonebookApplication : Application() {

    val appProvider: AppProvider by lazy { AppProvider() }

}

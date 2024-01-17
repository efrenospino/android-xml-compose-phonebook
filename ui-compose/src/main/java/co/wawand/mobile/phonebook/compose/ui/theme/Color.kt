package co.wawand.mobile.phonebook.compose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import co.wawand.mobile.phonebook.base.R

data class PhonebookColors(
    val purple_500: Color,
    val purple_700: Color,
    val teal_200: Color,
    val teal_700: Color,
    val black: Color,
    val gray: Color,
    val gray_700: Color,
    val white: Color
)

@Composable
fun phonebookColors() = PhonebookColors(
    purple_500 = colorResource(id = R.color.purple_500),
    purple_700 = colorResource(id = R.color.purple_700),
    teal_200 = colorResource(id = R.color.teal_200),
    teal_700 = colorResource(id = R.color.teal_700),
    black = colorResource(id = R.color.black),
    gray = colorResource(id = R.color.gray),
    gray_700 = colorResource(id = R.color.gray_700),
    white = colorResource(id = R.color.white),
)
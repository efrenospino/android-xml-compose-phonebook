package co.wawand.mobile.phonebook.compose.ui.feat.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.wawand.mobile.phonebook.compose.ui.theme.phonebookColors
import co.wawand.mobile.phonebook.data.model.Contact

@Composable
fun ContactCard(contact: Contact) {
    ElevatedCard(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = phonebookColors().white)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val gray = phonebookColors().gray
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = gray,
                            radius = this.size.maxDimension
                        )
                    },
                text = contact.firstLetter,
                style = MaterialTheme.typography.bodyLarge,
                color = phonebookColors().white
            )
            Column {
                Text(
                    text = contact.fullName,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = contact.phoneNumber,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewContactCard() {
    ContactCard(
        contact = Contact(
            firstName = "Steve",
            lastName = "Jobs",
            phoneNumber = "+112313213"
        )
    )
}
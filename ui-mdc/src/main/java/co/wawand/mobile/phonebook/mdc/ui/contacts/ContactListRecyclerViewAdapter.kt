package co.wawand.mobile.phonebook.mdc.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.wawand.mobile.phonebook.data.ext.flatWithId
import co.wawand.mobile.phonebook.data.model.ContactsGroup
import co.wawand.mobile.phonebook.mdc.databinding.ItemContactBinding
import co.wawand.mobile.phonebook.mdc.databinding.ItemContactSectionBinding
import java.util.UUID

class ContactListRecyclerViewAdapter :
    RecyclerView.Adapter<ContactListRecyclerViewAdapter.ItemViewHolder>() {

    private var flatContactList: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return if (viewType == ViewType.CONTACT.ordinal) {
            ItemViewHolder.ContactItemViewHolder(
                ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ItemViewHolder.SectionItemViewHolder(
                ItemContactSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemId(position: Int): Long {
        return flatContactList[position].id
    }

    override fun getItemViewType(position: Int): Int {
        if (flatContactList[position] is Item.Contact) {
            return ViewType.CONTACT.ordinal
        }
        return ViewType.SECTION.ordinal
    }

    override fun getItemCount(): Int = flatContactList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        when (holder) {
            is ItemViewHolder.ContactItemViewHolder -> {
                holder.binding.contact = (flatContactList[position] as Item.Contact).contact
            }

            is ItemViewHolder.SectionItemViewHolder -> {
                holder.binding.title = (flatContactList[position] as Item.Section).title
            }
        }
    }

    fun refreshContactList(contactList: List<ContactsGroup>) {

        val newFlatContactList = contactList.flatWithId(
            transformSection = { id, sectionName -> Item.Section(id, sectionName) },
            transformContact = { id, contact -> Item.Contact(id, contact) },
        )

        val diffCallback = DiffCallback(flatContactList, newFlatContactList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        flatContactList.clear()
        flatContactList.addAll(newFlatContactList)

        diffResult.dispatchUpdatesTo(this)
    }

    internal class DiffCallback(private val oldList: List<Item>, private val newList: List<Item>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

    enum class ViewType {
        SECTION, CONTACT
    }

    sealed class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class SectionItemViewHolder(val binding: ItemContactSectionBinding) :
            ItemViewHolder(binding.root)

        class ContactItemViewHolder(val binding: ItemContactBinding) :
            ItemViewHolder(binding.root)
    }

    sealed class Item(uuid: UUID) {

        val id: Long = uuid.mostSignificantBits and Long.MAX_VALUE

        data class Section(val uuid: UUID, val title: String) : Item(uuid)
        data class Contact(
            val uuid: UUID,
            val contact: co.wawand.mobile.phonebook.data.model.Contact
        ) : Item(uuid)
    }

}


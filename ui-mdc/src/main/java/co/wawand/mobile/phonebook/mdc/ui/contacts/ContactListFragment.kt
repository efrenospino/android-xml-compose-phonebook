package co.wawand.mobile.phonebook.mdc.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.wawand.mobile.phonebook.mdc.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    private val viewModel: ContactListViewModel by viewModels { ContactListViewModel.Factory }
    private val recyclerViewAdapter: ContactListRecyclerViewAdapter by lazy { ContactListRecyclerViewAdapter() }
    private lateinit var binding: FragmentContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.contactList.observe(this) {
            recyclerViewAdapter.refreshContactList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactSearchInput.editText?.addTextChangedListener {
            viewModel.searchContacts(it?.toString() ?: "")
        }
        binding.contactListRecyclerView.adapter = recyclerViewAdapter
    }

}

package com.picpay.desafio.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.view.adapter.UserListAdapter
import com.picpay.desafio.view.databinding.ContactsFragmentBinding
import com.picpay.desafio.view.model.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment: Fragment(R.layout.contacts_fragment) {

    private lateinit var binding: ContactsFragmentBinding

    private val viewModel: UserViewModel by viewModel()

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ContactsFragmentBinding.bind(view)

        initObservables()
        viewModel.fetchUsers()
        initRecyclerView()

    }

    private fun initObservables() {
        viewModel.users.observe(viewLifecycleOwner) { users ->
            if (users != null) {
                adapter.submitList(users)
                binding.userListProgressBar.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this@ContactsFragment.context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =  LinearLayoutManager(this@ContactsFragment.context)
            userListProgressBar.visibility = View.VISIBLE
        }
    }
}
package com.picpay.desafio.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.adapter.UserListAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.model.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModel()

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservables()
        viewModel.fetchUsers()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =  LinearLayoutManager(applicationContext)
            userListProgressBar.visibility = View.VISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservables() {
        viewModel.users.observe(this) { users ->
            if (users != null) {
                adapter.users = users
                adapter.notifyDataSetChanged()
                binding.userListProgressBar.visibility = View.GONE
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

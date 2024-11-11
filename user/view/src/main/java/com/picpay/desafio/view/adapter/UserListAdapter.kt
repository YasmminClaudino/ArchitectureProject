package com.picpay.desafio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.view.holder.UserListItemViewHolder
import com.picpay.desafio.user.data.model.UserResponse
import com.picpay.desafio.view.UserListDiffCallback
import com.picpay.desafio.view.databinding.ListItemUserBinding

class UserListAdapter : ListAdapter<UserResponse, UserListItemViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return UserListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}
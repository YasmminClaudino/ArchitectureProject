package com.picpay.desafio.view

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.user.data.model.UserResponse

class UserListDiffCallback : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem == newItem
    }
}
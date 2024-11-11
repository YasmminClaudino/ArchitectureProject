package com.picpay.desafio.view.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.view.databinding.ListItemUserBinding
import com.picpay.desafio.user.data.model.UserResponse
import com.picpay.desafio.view.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserResponse) {
        binding.name.text = user.name
        binding.username.text = user.username
        progressBarVisibility(true)
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    progressBarVisibility(false)
                }
                override fun onError(e: Exception) {
                    progressBarVisibility(false)
                }
            })
    }

    private fun progressBarVisibility(isVisible: Boolean) {
        binding.progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
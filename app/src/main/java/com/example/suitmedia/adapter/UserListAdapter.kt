package com.example.suitmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.data.remote.response.User
import com.example.suitmedia.databinding.ItemUserBinding

class UserListAdapter(private val onClick: (User) -> Unit) : PagingDataAdapter<User, UserListAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            "${data.firstName} ${data.lastName}".also { holder.binding.tvItemName.text = it }
            holder.binding.tvItemEmail.text = data.email
            Glide.with(holder.itemView)
                .load(data.avatar)
                .into(holder.binding.ivItemPhoto)
            holder.itemView.setOnClickListener { onClick(data) }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}
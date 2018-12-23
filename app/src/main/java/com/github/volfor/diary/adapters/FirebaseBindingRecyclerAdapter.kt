package com.github.volfor.diary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.BR

class FirebaseBindingRecyclerAdapter<T>(
    options: FirebaseRecyclerOptions<T>,
    private val layoutId: Int
) : FirebaseRecyclerAdapter<T, FirebaseBindingRecyclerAdapter.FirebaseViewHolder<T>>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)

        return FirebaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FirebaseViewHolder<T>, position: Int, model: T) {
        holder.bind(model)
    }

    class FirebaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}
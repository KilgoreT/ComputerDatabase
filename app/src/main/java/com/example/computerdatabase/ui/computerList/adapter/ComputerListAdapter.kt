package com.example.computerdatabase.ui.computerList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.computerdatabase.R
import com.example.computerdatabase.databinding.ItemComputerBinding
import com.example.computerdatabase.entity.Computer

class ComputerListAdapter: PagedListAdapter<Computer, ComputerListAdapter.ComputerViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComputerViewHolder {
        val binding: ItemComputerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_computer, parent, false)
        return ComputerViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ComputerViewHolder, position: Int) {
        getItem(position)?.let { computer -> holder.bind(computer) }
    }


    class ComputerViewHolder(private val binding: ItemComputerBinding): RecyclerView.ViewHolder(binding.root) {
        private val model = ComputerViewModel()
        fun bind(computer: Computer) {
            model.bind(computer)
            binding.vm = model
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Computer>() {
            override fun areItemsTheSame(oldItem: Computer, newItem: Computer): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Computer, newItem: Computer): Boolean =  oldItem.equals(newItem)
        }
    }

}
package com.example.workindia.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workindia.data.model.Item
import com.example.workindia.databinding.VerticalItemViewBinding

class VerticalAdapter(var products: List<Item>) : RecyclerView.Adapter<VerticalViewHolder>() {

    private lateinit var binding: VerticalItemViewBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = VerticalItemViewBinding.inflate(inflater, parent, false)
        return VerticalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        with(holder) {
            with(products[position]) {
                binding.tvItemName.text = this.name
                binding.tvItemPrice.text = this.price
                binding.tvExtra.text = this.extra
            }
        }
    }
}

class VerticalViewHolder(val binding: VerticalItemViewBinding) :
    RecyclerView.ViewHolder(binding.root)
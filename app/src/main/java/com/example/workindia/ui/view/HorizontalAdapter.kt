package com.example.workindia.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workindia.data.model.Item
import com.example.workindia.databinding.HorizontalItemViewBinding
import com.example.workindia.databinding.VerticalItemViewBinding

class HorizontalAdapter(var product: List<Item>) : RecyclerView.Adapter<HorizontalViewHolder>() {

    private lateinit var binding: HorizontalItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = HorizontalItemViewBinding.inflate(inflater, parent, false)
        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        with(holder) {
            with(product[position]) {
                binding.tvItemName.text = this.name
                binding.tvItemPrice.text = this.price
            }
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }
}

class HorizontalViewHolder(val binding: HorizontalItemViewBinding) :
    RecyclerView.ViewHolder(binding.root)



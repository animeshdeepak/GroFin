package com.grofin.feature.dashboard.service.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grofin.base.extensions.loadImage
import com.grofin.databinding.ItemServiceBinding
import com.grofin.feature.response.Category

class ServiceAdapter(private val serviceList: ArrayList<Category>?) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    var onItemClick: ((id: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemServiceBinding.inflate(inflater, parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) = holder.bind()

    override fun getItemCount() = serviceList?.size ?: -1

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val categoryItem = serviceList?.get(bindingAdapterPosition)

            binding.apply {
                category = categoryItem
                ivService.loadImage(categoryItem?.image)
                item.setOnClickListener {
                    onItemClick?.invoke(categoryItem?.id ?: -1, categoryItem?.image.orEmpty())
                }
                executePendingBindings()
            }
        }
    }
}
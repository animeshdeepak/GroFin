package com.grofin.feature.dashboard.service.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grofin.databinding.ItemServiceBinding
import com.grofin.feature.dashboard.service.model.ServiceModel

class ServiceAdapter(private val serviceList: ArrayList<ServiceModel>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    var onItemClick: ((serviceName: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemServiceBinding.inflate(inflater, parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) = holder.bind()

    override fun getItemCount() = serviceList.size

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val perItem = serviceList[bindingAdapterPosition]

            binding.apply {
                grofinItem = perItem
                binding.item.setOnClickListener {
                    onItemClick?.invoke(perItem.serviceName)
                }
                executePendingBindings()
            }
        }
    }
}
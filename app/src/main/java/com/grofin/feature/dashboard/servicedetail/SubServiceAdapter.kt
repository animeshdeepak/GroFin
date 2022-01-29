package com.grofin.feature.dashboard.servicedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grofin.databinding.ItemSubServiceBinding
import com.grofin.feature.response.SubCategory

class SubServiceAdapter(private val serviceList: ArrayList<SubCategory>) :
    RecyclerView.Adapter<SubServiceAdapter.SubServiceViewHolder>() {
    var onItemClick: ((serviceName: String, serviceUrl: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSubServiceBinding.inflate(inflater, parent, false)
        return SubServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubServiceViewHolder, position: Int) = holder.bind()

    override fun getItemCount() = serviceList.size

    inner class SubServiceViewHolder(private val binding: ItemSubServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val perItem = serviceList[bindingAdapterPosition]

            binding.apply {
                binding.item.setOnClickListener {
                    onItemClick?.invoke(perItem.name.orEmpty(), perItem.url.orEmpty())
                }
                executePendingBindings()
            }
        }
    }
}
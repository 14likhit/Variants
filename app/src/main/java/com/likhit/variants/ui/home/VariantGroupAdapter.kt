package com.likhit.variants.ui.home

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.likhit.variants.R
import com.likhit.variants.data.models.VariantGroup
import com.likhit.variants.databinding.LayoutVariantItemBinding
import com.likhit.variants.listeners.OnItemClickListener

class VariantGroupAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener<VariantGroup>) : RecyclerView.Adapter<VariantGroupAdapter.VariantGroupViewHolder>() {

    var variations: List<VariantGroup>? = null

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VariantGroupViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.context)
        }
        return VariantGroupViewHolder(layoutInflater!!.inflate(R.layout.layout_variant_item, viewGroup, false))
    }

    override fun onBindViewHolder(variantViewHolder: VariantGroupViewHolder, i: Int) {
        val variantGroup = variations!![i]
        variantViewHolder.binding!!.textVariationTitle.text = variantGroup.name
        variantViewHolder.binding.layoutItem.setOnClickListener { v -> onItemClickListener.onItemClick(variantGroup, variantViewHolder.adapterPosition, v) }
    }

    override fun getItemCount(): Int {
        return if (variations != null && variations!!.size > 0) {
            variations!!.size
        } else 0
    }

    inner class VariantGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: LayoutVariantItemBinding?

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}

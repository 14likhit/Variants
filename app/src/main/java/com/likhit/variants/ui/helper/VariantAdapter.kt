package com.likhit.variants.ui.helper

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.likhit.variants.R
import com.likhit.variants.data.models.Variation
import com.likhit.variants.databinding.LayoutVariantItemBinding
import com.likhit.variants.listeners.OnItemClickListener
import com.likhit.variants.ui.helper.VariantAdapter.VariantViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.net.URI
import java.net.URISyntaxException

class VariantAdapter(private val onItemClickListener: OnItemClickListener<Variation>) : RecyclerView.Adapter<VariantViewHolder>() {

    var variations: List<Variation>? = null

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VariantViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.context)
        }
        return VariantViewHolder(layoutInflater!!.inflate(R.layout.layout_variant_item, viewGroup, false))
    }

    override fun onBindViewHolder(variantViewHolder: VariantViewHolder, i: Int) {
        val variation = variations!![i]
        variantViewHolder.binding!!.textVariationTitle.text = variation.name
        val imageUrlNoParams = getUrlWithoutParameters(variation.logo)
        Picasso.get().load(variation.logo)
                .stableKey(imageUrlNoParams)
                .placeholder(R.drawable.ic_check_box)
                .fit()
                .into(variantViewHolder.binding.imageLogo, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {

                    }
                })
        variantViewHolder.binding.layoutItem.setOnClickListener { v -> onItemClickListener.onItemClick(variation, variantViewHolder.adapterPosition, v) }
    }

    private fun getUrlWithoutParameters(url: String): String {
        try {
            val uri = URI(url)
            return URI(uri.scheme,
                    uri.authority,
                    uri.path, null, // Ignore the query part of the input url
                    uri.fragment).toString()
        } catch (e: URISyntaxException) {
            return url
        }

    }

    override fun getItemCount(): Int {
        return if (variations != null && variations!!.size > 0) {
            variations!!.size
        } else 0
    }

    inner class VariantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: LayoutVariantItemBinding?

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}

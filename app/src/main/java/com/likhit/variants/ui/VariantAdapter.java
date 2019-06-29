package com.likhit.variants.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.variants.R;
import com.likhit.variants.data.models.Variation;
import com.likhit.variants.databinding.LayoutVariantItemBinding;
import com.likhit.variants.listeners.OnItemClickListener;

import java.util.List;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.VariantViewHolder> {

    private List<Variation> variations;

    private OnItemClickListener<Variation> onItemClickListener;

    private LayoutInflater layoutInflater;

    public VariantAdapter(OnItemClickListener<Variation> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public void setVariations(List<Variation> variations) {
        this.variations = variations;
    }

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new VariantAdapter.VariantViewHolder(layoutInflater.inflate(R.layout.layout_variant_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final VariantViewHolder variantViewHolder, int i) {
        final Variation variation = variations.get(i);
        variantViewHolder.binding.textVariationTitle.setText(variation.getName());
        variantViewHolder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(variation, variantViewHolder.getAdapterPosition(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (variations != null && variations.size() > 0) {
            return variations.size();
        }
        return 0;
    }

    public class VariantViewHolder extends RecyclerView.ViewHolder {

        private LayoutVariantItemBinding binding;

        public VariantViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

package com.likhit.variants.ui.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.variants.R;
import com.likhit.variants.data.models.VariantGroup;
import com.likhit.variants.databinding.LayoutVariantItemBinding;
import com.likhit.variants.listeners.OnItemClickListener;

import java.util.List;

public class VariantGroupAdapter extends RecyclerView.Adapter<VariantGroupAdapter.VariantGroupViewHolder> {

    private List<VariantGroup> variations;

    private OnItemClickListener<VariantGroup> onItemClickListener;

    private Context context;

    private LayoutInflater layoutInflater;

    public VariantGroupAdapter(Context context, OnItemClickListener<VariantGroup> onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public List<VariantGroup> getVariations() {
        return variations;
    }

    public void setVariations(List<VariantGroup> variations) {
        this.variations = variations;
    }

    @NonNull
    @Override
    public VariantGroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new VariantGroupViewHolder(layoutInflater.inflate(R.layout.layout_variant_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final VariantGroupViewHolder variantViewHolder, int i) {
        final VariantGroup variantGroup = variations.get(i);
        variantViewHolder.binding.textVariationTitle.setText(variantGroup.getName());
        variantViewHolder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(variantGroup, variantViewHolder.getAdapterPosition(), v);
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

    class VariantGroupViewHolder extends RecyclerView.ViewHolder {
        private LayoutVariantItemBinding binding;

        VariantGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

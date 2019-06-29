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

import java.util.List;

public class VariantGroupAdapter extends RecyclerView.Adapter<VariantGroupAdapter.VariantViewHolder> {

    private List<VariantGroup> variations;

    private Context context;

    private LayoutInflater layoutInflater;

    public VariantGroupAdapter(Context context) {
        this.context = context;
    }

    public List<VariantGroup> getVariations() {
        return variations;
    }

    public void setVariations(List<VariantGroup> variations) {
        this.variations = variations;
    }

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new VariantGroupAdapter.VariantViewHolder(layoutInflater.inflate(R.layout.layout_variant_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VariantViewHolder variantViewHolder, int i) {
        VariantGroup variantGroup = variations.get(i);
        variantViewHolder.binding.textVariationTitle.setText(variantGroup.getName());
    }

    @Override
    public int getItemCount() {
        if (variations != null && variations.size() > 0) {
            return variations.size();
        }
        return 0;
    }

    class VariantViewHolder extends RecyclerView.ViewHolder {
        private LayoutVariantItemBinding binding;

        VariantViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

package com.likhit.variants.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.likhit.variants.data.models.BaseResponse;

public class VariantsViewModel extends AndroidViewModel {

    private VariantsRepository variantsRepository;

    public VariantsViewModel(@NonNull Application application) {
        super(application);
        this.variantsRepository = new VariantsRepository();
    }

    MutableLiveData<BaseResponse> getVariants() {
        return variantsRepository.getVariants();
    }

}

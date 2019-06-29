package com.likhit.variants.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    protected void initViews(View view) {

    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showMessage(String message) {
        if (getBaseActivity() != null) {
            Toast.makeText(getBaseActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int messageResId) {
        if (getBaseActivity() != null) {
            Toast.makeText(getBaseActivity(), getBaseActivity().getString(messageResId), Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onBackPressed() {
        return false;
    }
}

package com.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment {

    @StringRes
    protected abstract int getText();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView textView = (TextView) v.findViewById(R.id.tv);
        textView.setText(getText());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastManager.getInstance().showToast(getText());
            }
        });

        return v;
    }
}

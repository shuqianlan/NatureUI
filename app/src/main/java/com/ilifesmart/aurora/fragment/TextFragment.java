package com.ilifesmart.aurora.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilifesmart.aurora.R;
import com.ilifesmart.aurora.interfact.ISettingItem;

public class TextFragment extends Fragment {
    public static final String EXTRA_TEXT = "extra_text";

    public static TextFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_text, container, false);
        ((TextView)v.findViewById(R.id.fragment_text)).setText(getArguments().getString(EXTRA_TEXT));

        return v;
    }

}

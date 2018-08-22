package com.ilifesmart.aurora.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ilifesmart.aurora.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LauncherActivity extends BaseActivity {
    public static final String TAG = "LauncherActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_launcher;
    }

    @OnClick(R.id.setting)
    public void onSettingClicked() {
        startActivity(SettingsActivity.newIntent(this));
    }
}

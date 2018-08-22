package com.ilifesmart.aurora.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.ilifesmart.aurora.config.SettingItemsHelper;
import com.ilifesmart.aurora.fragment.LoginFragment;
import com.ilifesmart.aurora.fragment.SettingMenuFragment;
import com.ilifesmart.aurora.R;
import com.ilifesmart.aurora.fragment.TextFragment;
import com.ilifesmart.aurora.interfact.ISettingItem;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity implements ISettingItem {

    @BindView(R.id.settings_menu)
    FrameLayout mSettingsMenu;
    @BindView(R.id.settings_info)
    FrameLayout mSettingsInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = fm.findFragmentById(R.id.settings_menu);
        if (fragment == null) {
            fragment = SettingMenuFragment.newInstance();
            if (fragment != null) {
                fm.beginTransaction()
                        .add(R.id.settings_menu, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onSettingItemSelected(SettingItemsHelper.ItemHolder holder) {
        Fragment fragment = null;
        switch (holder.id) {
            case SettingItemsHelper.SETTING_WIFI:
            case SettingItemsHelper.SETTING_bluetooth:
            case SettingItemsHelper.SETTING_Volume:
            case SettingItemsHelper.SETTING_about:
            case SettingItemsHelper.SETTING_System:
                fragment = TextFragment.newInstance(holder.title);
                break;
            case SettingItemsHelper.SETTING_user_login:
                fragment = LoginFragment.newInstance("hlkhjk_ok@hotmail.com", null);
                break;
            default:
                fragment = TextFragment.newInstance("none");
                break;
        }

        if (fragment != null) {
            fm.beginTransaction().replace(R.id.settings_info, fragment).commit();
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_settings;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }
}

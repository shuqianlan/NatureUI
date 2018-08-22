package com.ilifesmart.aurora.config;

import com.ilifesmart.aurora.MyApplication;
import com.ilifesmart.aurora.R;

import java.util.ArrayList;
import java.util.List;

public final class SettingItemsHelper {

    public static final String SETTING_WIFI = "wifi";
    public static final String SETTING_Volume = "volume";
    public static final String SETTING_System = "system";
    public static final String SETTING_user_login = "user&login";
    public static final String SETTING_about = "about";
    public static final String SETTING_bluetooth = "bluetooth";

    // TODO 配置Launcher的AI及可控设备.
//    public static final String SETTING_all_devs = "devs";
//    public static final String SETTING_edit_ais = "ais";
//    public static final String SETTING_edit_ep  = "ep";

    private static List<ItemHolder> sSettings = new ArrayList<>();

    public static void init() {
        sSettings.add(new ItemHolder(SETTING_WIFI, MyApplication.getContext().getResources().getString(R.string.settings_wifi)));
        sSettings.add(new ItemHolder(SETTING_bluetooth, MyApplication.getContext().getResources().getString(R.string.settings_bluetooth)));
        sSettings.add(new ItemHolder(SETTING_Volume, MyApplication.getContext().getResources().getString(R.string.settings_volume)));
        sSettings.add(new ItemHolder(SETTING_System, MyApplication.getContext().getResources().getString(R.string.settings_system)));
        sSettings.add(new ItemHolder(SETTING_about, MyApplication.getContext().getResources().getString(R.string.settings_about)));
        sSettings.add(new ItemHolder(SETTING_user_login, MyApplication.getContext().getResources().getString(R.string.settings_user_login)));
    }

    public static List<ItemHolder> getSettingItems() {
        return sSettings;
    }

    public static class ItemHolder {
        public String title;
        public String id;

        public ItemHolder(String id, String title) {
            this.title = title;
            this.id = id;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("title ").append(title).append(" ")
                    .append("id ").append(id)
                    .toString();
        }
    }
}

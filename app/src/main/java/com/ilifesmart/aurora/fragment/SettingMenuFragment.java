package com.ilifesmart.aurora.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilifesmart.aurora.R;
import com.ilifesmart.aurora.config.SettingItemsHelper;
import com.ilifesmart.aurora.interfact.ISettingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingMenuFragment extends BaseFragment {

    public static final String TAG = "SettingItems";
    private MenuItem mSelected;
    @BindView(R.id.settings_recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;

    private ISettingItem mItemSelected;
    private List<SettingItemsHelper.ItemHolder> mMenuItems;

    public static SettingMenuFragment newInstance() {

        Bundle args = new Bundle();
        SettingMenuFragment fragment = new SettingMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMenuItems = SettingItemsHelper.getSettingItems();
        Log.d(TAG, "onCreate: menu " + mMenuItems);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(new MenuAdapter());
        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                MenuItem item = (MenuItem) mRecycler.findViewHolderForLayoutPosition(0);
                item.onClick(null);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.settings_back)
    public void onBackClicked() {
        getActivity().finish();
    }

    public class MenuItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {
        private SettingItemsHelper.ItemHolder holder;
        private TextView mTitle;
        private LinearLayout mContainer;

        public MenuItem(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnTouchListener(this);
            mTitle = itemView.findViewById(R.id.item_title);
            mContainer = itemView.findViewById(R.id.setting_item_cont);
        }

        public void onBind(SettingItemsHelper.ItemHolder item) {
            holder = item;
            mTitle.setText(item.title);
        }

        @Override
        public void onClick(View v) {
            if (mSelected != null) {
                if (mSelected.holder.id.equals(holder.id)) {
                    return;
                }
                mSelected.mContainer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mSelected.mTitle.setTextColor(Color.BLACK);
            }

            mSelected = this;
            mSelected.mContainer.setBackgroundColor(Color.DKGRAY);
            mSelected.mTitle.setTextColor(Color.WHITE);
            mItemSelected.onSettingItemSelected(holder);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                itemView.callOnClick();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            }

            return false;
        }
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuItem> {

        @NonNull
        @Override
        public MenuItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_setting_menu, parent, false);

            return new MenuItem(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuItem holder, int position) {
            holder.onBind(mMenuItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mMenuItems.size();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mItemSelected = (ISettingItem)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mItemSelected = null;
    }

}

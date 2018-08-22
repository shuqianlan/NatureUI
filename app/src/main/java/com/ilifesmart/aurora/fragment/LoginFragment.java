package com.ilifesmart.aurora.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.ilifesmart.aurora.activity.BaseActivity;
import com.ilifesmart.aurora.R;
import com.ilifesmart.aurora.widgets.LSExecutionIndicateButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment {

    public static final String TAG = "LoginFragment";
    public static final String EXTRA_UID = "EXTRA_UID";
    public static final String EXTRA_PWD = "EXTRA_PWD";
    Unbinder unbinder;
    @BindView(R.id.uid)
    AutoCompleteTextView mUid;
    @BindView(R.id.pwd)
    EditText mPwd;
    @BindView(R.id.login)
    LSExecutionIndicateButton mLogin;

    public static LoginFragment newInstance(String uid, String pwd) {

        Bundle args = new Bundle();
        args.putString(EXTRA_UID, uid);
        args.putString(EXTRA_PWD, pwd);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mUid = v.findViewById(R.id.uid);
        mPwd = v.findViewById(R.id.pwd);
        if (getArguments() != null) {
            String uid = getArguments().getString(EXTRA_UID);
            String pwd = getArguments().getString(EXTRA_PWD);

            if (!TextUtils.isEmpty(uid)) {
                mUid.setText(uid);
            }

            if (!TextUtils.isEmpty(pwd)) {
                mPwd.setText(pwd);
            }
        }

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login)
    public void onLoginClicked() {
        if (mLogin.isExecutingAnimation()) {
            return;
        }

        new LoginTask().execute(mUid.getText().toString(), mPwd.getText().toString());
    }

    // test
    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLogin.startExecutingAnimation();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(4_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            mLogin.stopExecutingAnimation();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mLogin.stopExecutingAnimation();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((BaseActivity)getActivity()).registerTouchListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((BaseActivity)getActivity()).unregisterTouchListener(this);
    }
}

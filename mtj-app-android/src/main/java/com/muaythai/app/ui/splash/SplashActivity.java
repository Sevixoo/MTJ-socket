package com.muaythai.app.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muaythai.app.MTJApplication;
import com.muaythai.app.R;
import com.muaythai.app.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.View {

    @BindView(R.id.textView_version)
    public TextView mVersionTextView;

    @BindView(R.id.progressBar)
    public ProgressBar mProgressBar;

    @Inject
    public SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MTJApplication.get(getBaseContext()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.setView(this);
        mPresenter.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.setView(null);
    }

    @Override
    public void displayVersionName(String versionName) {
        mVersionTextView.setText(versionName);
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayError(Throwable error) {
        Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_LONG).show();
    }

}

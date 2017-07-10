package com.muaythai.app.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.muaythai.app.MTJApplication;
import com.muaythai.app.R;
import com.muaythai.application.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private EditText mNameEditText;
    private EditText mPasswordEditText;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mNameEditText = (EditText)findViewById(R.id.editText_name);
        mPasswordEditText = (EditText)findViewById(R.id.editText_pass);
        /*mPresenter = MTJApplication.get(getBaseContext()).provideLoginPresenter();
        mPresenter.setView(this);
        mPresenter.initialize();*/
    }

    @Override
    protected void onDestroy() {
        mPresenter.setView(null);
    }

    public void onClickLogin(View view){
        String login = mNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mPresenter.login( login, password );
    }

    @Override
    public void displayError(Throwable error) {

    }
}

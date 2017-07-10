package com.muaythai.app.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.muaythai.app.R;
import com.muaythai.app._old.ClientActivity;
import com.muaythai.app._old.ServerActivity;
import com.muaythai.app.ui.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageButton_settings)
    public void onClickSettings(View view){
        Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_client)
    public void onClickClientButton(View view){
        Intent intent = new Intent(getBaseContext(), ClientActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_server)
    public void onClickServerButton(View view){
        Intent intent = new Intent(getBaseContext(), ServerActivity.class);
        startActivity(intent);
    }


}

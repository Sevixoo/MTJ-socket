package com.muaythai.app._old;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.muaythai.app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
    }

    public void onClickClientButton(View view){
        Intent intent = new Intent(getBaseContext(), ClientActivity.class);
        startActivity(intent);
    }

    public void onClickServerButton(View view){
        Intent intent = new Intent(getBaseContext(), ServerActivity.class);
        startActivity(intent);
    }

}

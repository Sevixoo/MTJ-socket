package com.muaythai.app._old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.muaythai.app.MTJApplication;
import com.muaythai.app.R;
import com.muaythai.application.connection.p2p.IConnectionListener;
import com.muaythai.application.connection.p2p.MTJClient;
import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.p2p.IServerConnection;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientActivity extends AppCompatActivity implements IConnectionListener {

    @Inject MTJClient mMTJClient;

    @BindView(R.id.list_view)
    ListView mListView;

    @BindView(R.id.button_connect)
    Button mConnectButton;

    @BindView(R.id.button_send)
    Button mSendButton;

    @BindView(R.id.editText_message)
    EditText mMessageEditText;

    private StableArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        ButterKnife.bind(this);
        MTJApplication.get(getBaseContext()).getAppComponent().inject(this);

        mAdapter = new StableArrayAdapter(this);
        mListView.setAdapter(mAdapter);
        mMTJClient.setConnectionListener(this);
        checkConnectionState();
    }

    @OnClick(R.id.button_connect)
    public void onClickConnectButton(View view){
        Button btn = (Button)view;
        if(mMTJClient.isConnected()){
            mMTJClient.disconnect();
            mConnectButton.setText("CONNECT");
        }else{
            try {
                mMTJClient.login("AAA","BBB");
                mConnectButton.setText("CLOSE");
            } catch (IOException e) {
                e.printStackTrace();
                displayMessage(e.getMessage());
            }
        }
    }

    @OnClick(R.id.button_send)
    public void onClickButtonSend(View view){
        if(mMTJClient.isConnected()){
            mMTJClient.send(mMessageEditText.getText().toString());
            mMessageEditText.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMTJClient.disconnect();
    }

    @Override
    public void onConnected(IServerConnection serverConnection) {
        displayMessage("Connected to server");
        checkConnectionState();
    }

    @Override
    public void onConnectError(Throwable throwable) {
        displayMessage("connection error: " + throwable.getMessage());
        checkConnectionState();
    }

    @Override
    public void onDisconnected(IServerConnection serverConnection) {
        displayMessage("disconnected");
        checkConnectionState();
    }

    @Override
    public void onMessage(IMessage message) {
        displayMessage("message: " + message.getClass().getSimpleName() );
    }

    private void checkConnectionState(){
        mConnectButton.post(new Runnable() {
            @Override
            public void run() {
                mSendButton.setEnabled(mMTJClient.isConnected());
                mMessageEditText.setEnabled((mMTJClient.isConnected()));
                if(mMTJClient.isConnected()){
                    mConnectButton.setText("CLOSE");
                }else{
                    mConnectButton.setText("CONNECT");
                }
            }
        });
    }

    private void displayMessage(final String message){
        mListView.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.add(message);
                mListView.smoothScrollToPosition(mAdapter.getCount()-1);
            }
        });
    }

}

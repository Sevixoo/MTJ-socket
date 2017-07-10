package com.muaythai.app._old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.muaythai.app.MTJApplication;
import com.muaythai.app.R;
import com.muaythai.core.login.SendMessageCommand;
import com.muaythai.core.protocol.p2p.IClientConnection;
import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.server.connection.p2p.IServerListeners;
import com.muaythai.server.connection.p2p.MTJServer;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServerActivity extends AppCompatActivity implements IServerListeners {

    @Inject MTJServer mMTJServer;

    @BindView(R.id.list_view)
    ListView mListView;

    private StableArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);
        MTJApplication.get(getBaseContext()).getAppComponent().inject(this);

        mAdapter = new StableArrayAdapter(this);
        mListView.setAdapter(mAdapter);
        mMTJServer.setServerListener(this);
    }

    public void onClickStart(View view){
        Button btn = (Button)view;
        if(!mMTJServer.isStarted()){
            try {
                mMTJServer.start();
                btn.setText("STOP");
            } catch (IOException e) {
                e.printStackTrace();
                displayMessage(e.getMessage());
            }
        }else{
            mMTJServer.stop();
            btn.setText("START");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMTJServer.stop();
    }

    @Override
    public void onCreated(int port) {
        displayMessage("Server started: listening on port:" + port);
    }

    @Override
    public void onConnected(IClientConnection connection) {
        displayMessage("Client connected");
    }

    @Override
    public void onDisconnected(IClientConnection connection) {
        displayMessage("Client disconnected");
    }

    @Override
    public void onDestroyed() {
        displayMessage("Server shutdown");
    }

    @Override
    public void onMessage(ICommand message) {
        if(message instanceof SendMessageCommand){
            displayMessage("Received message: " + ((SendMessageCommand) message).getMessage());
        }else{
            displayMessage("Received message: " + message.getClass().getSimpleName());
        }
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

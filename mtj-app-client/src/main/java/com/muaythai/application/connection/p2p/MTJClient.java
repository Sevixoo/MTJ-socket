package com.muaythai.application.connection.p2p;

import com.muaythai.core.login.SendMessageCommand;
import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.p2p.IMessageDispatcher;

import java.io.IOException;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class MTJClient{

    private IServerSocketConnection mConnection;

    private IConnectifyProvider mConnectifyProvider;
    private IMessageDispatcher mMessageDispatcher;

    private IConnectionListener mConnectionListener;

    private int mPort;

    private ReadThread mReadThread;
    private ConnectThread mConnectThread;

    public MTJClient(IConnectifyProvider connectifyProvider, int port) {
        mConnection = new SocketChannel();
        mConnectifyProvider = connectifyProvider;
        mMessageDispatcher = new MTJClientDispatcher();
        mPort = port;
    }

    public void login(String name, String password)throws IOException{
        mConnectThread = new ConnectThread();
        mConnectThread.start();
    }

    public boolean isConnected() {
        return mConnection.isConnected();
    }

    public void disconnect() {
        mConnection.disconnect();
        mConnectionListener.onDisconnected(mConnection);
    }

    public void setConnectionListener(IConnectionListener clientListener) {
        this.mConnectionListener = clientListener;
    }

    public void send(String message) {
        mConnection.send(new SendMessageCommand(message));
    }

    private class ConnectThread extends Thread{
        @Override
        public void run() {
            String ipAddress = mConnectifyProvider.provideIPAddress();
            try {
                mConnection.connect(ipAddress,mPort);
                mReadThread = new ReadThread();
                mReadThread.start();
                mMessageDispatcher.onConnected(mConnection);
                mConnectionListener.onConnected(mConnection);
            } catch (IOException e) {
                e.printStackTrace();
                mMessageDispatcher.onConnectError(e);
                mConnectionListener.onConnectError(new IOException(e.getMessage() + " " + ipAddress + ":" + mPort ));
            }
        }
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            while (mConnection.isConnected()) {
                try {
                    IMessage message = mConnection.read();
                    message.dispatch(mMessageDispatcher,mConnection);
                    mConnectionListener.onMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    disconnect();
                }
            }
        }
    }

}

package com.muaythai.server.connection.p2p;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.ICommandDispatcher;
import com.muaythai.core.protocol.p2p.IMessage;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class MTJServer implements MTJServerConnection{

    private IServerConnection mServerConnection;
    private ICommandDispatcher mDispatcher;

    private Thread mAcceptThread;
    private ReadThread mReadThread;

    private IServerListeners mServerListener;

    private List<IClientSocketConnection> mClients;

    private int mPort;

    public MTJServer(int port) {
        mPort = port;
        mServerConnection = new ServerSocketChannel(port);
        mDispatcher = new MTJServerDispatcher(this);
        mClients = new ArrayList<>();
    }

    public void setServerListener(IServerListeners mServerListener) {
        this.mServerListener = mServerListener;
    }

    public synchronized void start()throws IOException{
        mServerConnection.bind();
        mClients.clear();
        mAcceptThread = new AcceptThread();
        mAcceptThread.start();
        mDispatcher.onCreated();
        mServerListener.onCreated(mPort);
    }

    public synchronized void stop(){
        for (IClientSocketConnection client : mClients) {
            client.disconnect();
        }
        mClients.clear();
        mServerConnection.close();
        if(mAcceptThread!=null){
            mDispatcher.onDestroyed();
            mServerListener.onDestroyed();
        }
        mAcceptThread = null;
        mReadThread = null;
    }

    public synchronized boolean isStarted(){
        return mAcceptThread != null;
    }

    private class AcceptThread extends Thread{
        @Override
        public void run() {
            while (mServerConnection!=null){
                try {
                    IClientSocketConnection clientConnection = mServerConnection.accept();
                    mClients.add(clientConnection);
                    mReadThread = new ReadThread(clientConnection);
                    mReadThread.start();
                    mDispatcher.onConnected(clientConnection);
                    mServerListener.onConnected(clientConnection);
                }catch (IOException ex){
                    ex.printStackTrace();
                    MTJServer.this.stop();
                    break;
                }
            }
        }
    }

    @Override
    public void sendToAll(IMessage message) {
        for (IClientSocketConnection mClient : mClients) {
            if(mClient.isConnected()){
                mClient.send(message);
            }
        }
    }

    private void disconnectConnection(IClientSocketConnection connection){
        connection.disconnect();
        mDispatcher.onDisconnected(connection);
        mServerListener.onDisconnected(connection);
        mClients.remove(connection);
    }

    private class ReadThread extends Thread{

        private IClientSocketConnection mConnection;

        ReadThread(IClientSocketConnection mConnection) {
            this.mConnection = mConnection;
        }

        @Override
        public void run() {
            while (mConnection.isConnected()){
                try {
                    ICommand message = mConnection.read();
                    message.dispatch(mDispatcher,mConnection);
                    mServerListener.onMessage(message);
                } catch (IOException e) {
                    if(e instanceof SocketTimeoutException){
                        e.printStackTrace();
                        if(mConnection.isClosed()){
                            break;
                        }
                        continue;
                    }
                    e.printStackTrace();
                    break;
                }
            }
            mConnection.disconnect();
            mClients.remove(mConnection);
            disconnectConnection(mConnection);
        }
    }

}

package com.muaythai.server.connection.rest;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.rest.IRequest;
import com.muaythai.server.connection.p2p.IClientSocketConnection;
import com.muaythai.server.connection.p2p.IServerConnection;
import com.muaythai.server.connection.p2p.MTJServer;
import com.muaythai.server.connection.p2p.ServerSocketChannel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class MTJRestServer {

    private Thread mAcceptThread;
    private MTJRestServer.ReadThread mReadThread;
    private ServerSocket mServerSocket;
    private int mPort;

    private List<ClientSocketConnection> mClients;
    private Router mRouter;

    public MTJRestServer(int port) {
        mPort = port;
        mClients = new ArrayList<>();
        mRouter = new Router();
    }

    public void bind()throws IOException{
        SocketAddress socketAddress = new InetSocketAddress((InetAddress) null,mPort);
        mServerSocket = new ServerSocket();
        mServerSocket.bind(socketAddress);
    }

    public ClientSocketConnection accept()throws IOException{
        Socket socket = mServerSocket.accept();
        return new ClientSocketConnection(socket);
    }

    public void close(){
        try {
            mServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start()throws IOException{
        bind();
        mClients.clear();
        mAcceptThread = new MTJRestServer.AcceptThread();
        mAcceptThread.start();
    }

    public synchronized void stop(){
        close();
        mAcceptThread = null;
        mReadThread = null;
    }

    public synchronized boolean isStarted(){
        return mAcceptThread != null;
    }

    private class AcceptThread extends Thread{
        @Override
        public void run() {
            while (mServerSocket!=null){
                try {
                    ClientSocketConnection clientConnection = accept();
                    mClients.add(clientConnection);
                    mReadThread = new MTJRestServer.ReadThread(clientConnection);
                    mReadThread.start();
                }catch (IOException ex){
                    ex.printStackTrace();
                    MTJRestServer.this.stop();
                    break;
                }
            }
        }
    }

    private class ReadThread extends Thread{

        private ClientSocketConnection mConnection;

        ReadThread(ClientSocketConnection mConnection) {
            this.mConnection = mConnection;
        }

        @Override
        public void run() {
            while (mConnection.isConnected()){
                try {
                    IRequest request = mConnection.read();
                    mConnection.send(mRouter.dispatch(request));
                } catch (IOException e) {
                    e.printStackTrace();
                    mConnection.disconnect();
                    mClients.remove(mConnection);
                    break;
                }
            }
        }
    }


}

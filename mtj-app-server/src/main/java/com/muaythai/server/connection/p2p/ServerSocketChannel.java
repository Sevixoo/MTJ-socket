package com.muaythai.server.connection.p2p;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class ServerSocketChannel implements IServerConnection{

    private ServerSocket mServerSocket;

    private int mPort;

    public ServerSocketChannel(int port) {
        mPort = port;
    }

    public void bind()throws IOException{
        SocketAddress socketAddress = new InetSocketAddress((InetAddress) null,mPort);
        mServerSocket = new ServerSocket();
        mServerSocket.bind(socketAddress);
    }

    public IClientSocketConnection accept()throws IOException{
        Socket socket = mServerSocket.accept();
        return new ClientSocketConnection(socket);
    }

    @Override
    public void close(){
        try {
            mServerSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

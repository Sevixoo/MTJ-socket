package com.muaythai.application.connection.p2p;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.IMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by pi19124 on 09.06.2017.
 */

final class SocketChannel implements IServerSocketConnection {

    private Socket mSocket;

    SocketChannel() { }

    @Override
    public void connect( String ipAddress , int port )throws IOException {
        mSocket = new Socket();
        String[] splitIPAddress = ipAddress.split("\\.");
        String baseIPAddress = splitIPAddress[0] + "." + splitIPAddress[1] + "." + splitIPAddress[2];
        for(int i = 1; i < 255 && !mSocket.isConnected(); i++){
            try {
                SocketAddress socketAddress = new InetSocketAddress(baseIPAddress + "." + i,port);
                mSocket.connect(socketAddress,300);
            }catch (IOException ex){
                if( i == 254){ throw ex; }
            }
        }
    }

    @Override
    public void send(ICommand command){
        try {
            OutputStream outputStream = mSocket.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(command);
            outputStream.write(bos.toByteArray());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }

    @Override
    public IMessage read() throws IOException {
        InputStream inputStream = mSocket.getInputStream();
        ObjectInput in = new ObjectInputStream(inputStream);
        IMessage message = null;
        try {
            message = (IMessage)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return message;
    }

    @Override
    public void disconnect(){
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        return mSocket != null && mSocket.isConnected();
    }
}

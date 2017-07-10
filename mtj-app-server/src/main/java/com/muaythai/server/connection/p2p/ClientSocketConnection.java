package com.muaythai.server.connection.p2p;

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
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class ClientSocketConnection implements IClientSocketConnection {

    private Socket mSocket;

    public ClientSocketConnection(Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void send(IMessage message){
        try {
            OutputStream outputStream = mSocket.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(message);
            outputStream.write(bos.toByteArray());
            outputStream.flush();
        }catch (IOException ex){
            disconnect();
        }
    }

    public ICommand read()throws IOException{
        InputStream inputStream = mSocket.getInputStream();
        ObjectInput in = new ObjectInputStream(inputStream);
        ICommand message = null;
        try {
            message = (ICommand)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return message;
    }

    @Override
    public boolean isConnected() {
        return mSocket.isConnected();
    }

    @Override
    public boolean isClosed() {
        return mSocket.isClosed();
    }

    @Override
    public void disconnect() {
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

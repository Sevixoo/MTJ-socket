package com.muaythai.server.connection.rest;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.rest.IRequest;
import com.muaythai.core.protocol.rest.IResponse;
import com.muaythai.server.connection.p2p.IClientSocketConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class ClientSocketConnection{

    private Socket mSocket;

    public ClientSocketConnection(Socket socket) {
        this.mSocket = socket;
    }

    public void send(IResponse message){
        try {
            OutputStream outputStream = mSocket.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(message);
            outputStream.write(bos.toByteArray());
            outputStream.flush();
        }catch (IOException ex){
            try {
                mSocket.close();
            } catch (IOException ignored) {}
        }
    }

    public IRequest read()throws IOException{
        InputStream inputStream = mSocket.getInputStream();
        ObjectInput in = new ObjectInputStream(inputStream);
        IRequest message = null;
        try {
            message = (IRequest)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return message;
    }

    public boolean isConnected() {
        return mSocket.isConnected();
    }

    public void disconnect() {
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

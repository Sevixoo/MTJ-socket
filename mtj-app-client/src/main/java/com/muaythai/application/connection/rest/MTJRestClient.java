package com.muaythai.application.connection.rest;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.rest.IRequest;
import com.muaythai.core.protocol.rest.IResponse;
import com.muaythai.core.protocol.rest.IRestClient;

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

/**
 * Created by pi19124 on 12.06.2017.
 */

public class MTJRestClient implements IRestClient {

    private Socket mSocket;

    public MTJRestClient() { }

    public void connect( String ipAddress , int port )throws IOException {
        mSocket = new Socket();
        String[] splitIPAddress = ipAddress.split("\\.");
        String baseIPAddress = splitIPAddress[0] + "." + splitIPAddress[1] + "." + splitIPAddress[2];
        for(int i = 1; i < 255 && !mSocket.isConnected(); i++){
            try {
                SocketAddress socketAddress = new InetSocketAddress(baseIPAddress + "." + i,port);
                mSocket.connect(socketAddress);
            }catch (IOException ex){
                if( i == 254){ throw ex; }
            }
        }
    }

    @Override
    public <D extends IResponse> D request(IRequest request) {
        try {
            OutputStream outputStream = mSocket.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(request);
            outputStream.write(bos.toByteArray());
            outputStream.flush();

            InputStream inputStream = mSocket.getInputStream();
            ObjectInput in = new ObjectInputStream(inputStream);
            try {
                return (D)in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
        return null;
    }

    public void disconnect(){
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return mSocket != null && mSocket.isConnected();
    }

}

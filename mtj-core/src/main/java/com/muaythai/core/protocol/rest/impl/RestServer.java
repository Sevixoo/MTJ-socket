package com.muaythai.core.protocol.rest.impl;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.rest.IDispatcher;
import com.muaythai.core.protocol.rest.IRequest;
import com.muaythai.core.protocol.rest.IResponse;
import com.muaythai.core.protocol.rest.Request;
import com.muaythai.core.protocol.rest.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pi19124 on 14.06.2017.
 */

public class RestServer {

    private ServerSocket        mServerSocket;
    private ExecutorService     mExecutorService;
    private AcceptThread        mAcceptThread;

    private int mSocketTimeout = 2000;

    private IDispatcher         mDispatcher;

    public RestServer( IDispatcher dispatcher ) {
        mDispatcher = dispatcher;
    }

    public void start(int port)throws IOException{
        if(mServerSocket == null) {
            mExecutorService = Executors.newFixedThreadPool(5);
            SocketAddress socketAddress = new InetSocketAddress((InetAddress) null, port);
            mServerSocket = new ServerSocket();
            mServerSocket.bind(socketAddress);
            mAcceptThread = new AcceptThread();
            mAcceptThread.start();
        }
    }

    public void stop(){
        try {
            mExecutorService.shutdownNow();
            mExecutorService = null;
            mServerSocket.close();
            mAcceptThread = null;
            mServerSocket = null;
        } catch (Exception ignored) {}
    }

    private class AcceptThread extends Thread{
        @Override
        public void run() {
            while (mServerSocket!=null){
                try {
                    Socket socket = mServerSocket.accept();
                    socket.setSoTimeout(mSocketTimeout);
                    mExecutorService.execute(new RequestTask(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RequestTask implements Runnable{
        private Socket mSocket;

        RequestTask(Socket mSocket) {
            this.mSocket = mSocket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = mSocket.getInputStream();
                ObjectInput in = new ObjectInputStream(inputStream);
                Response response = null;
                try {
                    Request request = (Request)in.readObject();
                    IRequest requestBody = request.getRequest();
                    IResponse responseBody = mDispatcher.dispatch(requestBody);
                    response = new Response(responseBody,Response.RESULT_OK);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    response = new Response(Response.RESULT_NOT_FOUND);
                }
                OutputStream outputStream = mSocket.getOutputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(response);
                outputStream.write(bos.toByteArray());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mSocket.close();
            } catch (IOException igonred) {}
        }
    }


}

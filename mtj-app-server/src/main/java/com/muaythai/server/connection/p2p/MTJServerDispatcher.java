package com.muaythai.server.connection.p2p;

import com.muaythai.core.login.LoginSuccessMessage;
import com.muaythai.core.login.SendMessageCommand;
import com.muaythai.core.login.WelcomeMessage;
import com.muaythai.core.protocol.p2p.ICommandDispatcher;
import com.muaythai.core.protocol.p2p.IClientConnection;
import com.muaythai.core.login.LoginCommand;
import com.muaythai.server.connection.p2p.MTJServerConnection;

/**
 * Created by pi19124 on 09.06.2017.
 */

public final class MTJServerDispatcher implements ICommandDispatcher {

    private MTJServerConnection mServerConnection;

    public MTJServerDispatcher(MTJServerConnection mServerConnection){
        this.mServerConnection = mServerConnection;
    }

    @Override
    public void onCreated() {
        System.out.println( "Server started" );
    }

    @Override
    public void onConnected(IClientConnection connection) {
        connection.send(new WelcomeMessage());
        System.out.println( "Client connected" );
    }

    @Override
    public void onDisconnected(IClientConnection connection) {
        System.out.println( "Client disconnected" );
    }

    @Override
    public void onDestroyed() {
        System.out.println( "Server shutdown" );
    }

    @Override
    public void dispatch(LoginCommand loginCommand, IClientConnection connection) {
        mServerConnection.sendToAll(new LoginSuccessMessage());
    }

    @Override
    public void onMessageCommand(SendMessageCommand sendMessageCommand, IClientConnection sender) {
        System.out.println( "onMessageCommand:" );
    }
}

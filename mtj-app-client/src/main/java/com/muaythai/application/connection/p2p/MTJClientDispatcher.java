package com.muaythai.application.connection.p2p;

import com.muaythai.core.login.LoginSuccessMessage;
import com.muaythai.core.login.WelcomeMessage;
import com.muaythai.core.protocol.p2p.IMessageDispatcher;
import com.muaythai.core.protocol.p2p.IServerConnection;
/**
 * Created by pi19124 on 09.06.2017.
 */
public final class MTJClientDispatcher implements IMessageDispatcher {

    public MTJClientDispatcher() { }

    @Override
    public void onConnected(IServerConnection serverConnection) {
        System.err.println("onConnected");
    }

    @Override
    public void onConnectError(Throwable throwable) {
        System.err.println("onConnectError");
    }

    @Override
    public void onDisconnected(IServerConnection serverConnection) {
        System.err.println("onDisconnected");
    }

    @Override
    public void onWelcomeMessage(WelcomeMessage welcomeMessage, IServerConnection connection) {
        System.err.println("onWelcomeMessage");
    }

    @Override
    public void dispatch(LoginSuccessMessage loginSuccessMessage, IServerConnection connection) {
        System.err.println("LoginSuccessMessage");
    }
}

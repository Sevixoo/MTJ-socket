package com.muaythai.server.connection.p2p;

import com.muaythai.core.login.WelcomeMessage;
import com.muaythai.core.protocol.p2p.IClientConnection;
import com.muaythai.core.protocol.p2p.ICommand;

/**
 * Created by pi19124 on 13.06.2017.
 */

public interface IServerListeners {

    void onCreated(int port);

    void onConnected(IClientConnection connection);

    void onDisconnected(IClientConnection connection);

    void onDestroyed();

    void onMessage(ICommand message);
}

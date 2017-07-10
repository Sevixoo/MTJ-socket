package com.muaythai.application.connection.p2p;

import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.p2p.IServerConnection;

/**
 * Created by pi19124 on 14.06.2017.
 */

public interface IConnectionListener {

    void onConnected(IServerConnection serverConnection);

    void onConnectError(Throwable throwable);

    void onDisconnected(IServerConnection serverConnection);

    void onMessage(IMessage message);

}

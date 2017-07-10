package com.muaythai.server.connection.p2p;

import com.muaythai.core.protocol.p2p.IMessage;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface MTJServerConnection {

    void sendToAll(IMessage message);

}

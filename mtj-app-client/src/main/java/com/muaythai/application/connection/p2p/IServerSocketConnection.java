package com.muaythai.application.connection.p2p;

import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.p2p.IServerConnection;

import java.io.IOException;

/**
 * Created by pi19124 on 09.06.2017.
 */

interface IServerSocketConnection extends IServerConnection {

    void connect(String ipAddress, int port)throws IOException;

    IMessage read()throws IOException;

}

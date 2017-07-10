package com.muaythai.server.connection.p2p;

import com.muaythai.core.protocol.p2p.IClientConnection;
import com.muaythai.core.protocol.p2p.ICommand;

import java.io.IOException;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface IClientSocketConnection extends IClientConnection {

    boolean isConnected();

    ICommand read()throws IOException;

    boolean isClosed();
}

package com.muaythai.core.protocol.p2p;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface IClientConnection {

    void send( IMessage message );

    void disconnect();

    boolean isConnected();

}

package com.muaythai.core.protocol.p2p;

/**
 * Created by pi19124 on 09.06.2017.
 *
 * This interface allows send Commands to server
 */
public interface IServerConnection {

    void send(ICommand command);

    void disconnect();

    boolean isConnected();

}

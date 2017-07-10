package com.muaythai.server.connection.p2p;


import java.io.IOException;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface IServerConnection {

    void bind()throws IOException;

    IClientSocketConnection accept()throws IOException;

    void close();
}

package com.muaythai.core.protocol.p2p;

import java.io.Serializable;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface IMessage extends Serializable {

    void dispatch( IMessageDispatcher dispatcher , IServerConnection connection );

}

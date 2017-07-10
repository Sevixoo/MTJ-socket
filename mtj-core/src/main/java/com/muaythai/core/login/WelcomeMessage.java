package com.muaythai.core.login;

import com.muaythai.core.protocol.p2p.IMessage;
import com.muaythai.core.protocol.p2p.IMessageDispatcher;
import com.muaythai.core.protocol.p2p.IServerConnection;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class WelcomeMessage implements IMessage {
    @Override
    public void dispatch(IMessageDispatcher dispatcher, IServerConnection connection) {
        dispatcher.onWelcomeMessage(this,connection);
    }
}

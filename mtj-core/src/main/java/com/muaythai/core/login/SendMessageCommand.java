package com.muaythai.core.login;

import com.muaythai.core.protocol.p2p.IClientConnection;
import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.ICommandDispatcher;

/**
 * Created by pi19124 on 14.06.2017.
 */

public class SendMessageCommand implements ICommand{

    private String message;

    public SendMessageCommand(String message) {
        this.message = message;
    }

    @Override
    public void dispatch(ICommandDispatcher dispatcher, IClientConnection sender) {
        dispatcher.onMessageCommand(this,sender);
    }

    public String getMessage() {
        return message;
    }
}

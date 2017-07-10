package com.muaythai.core.login;

import com.muaythai.core.protocol.p2p.ICommand;
import com.muaythai.core.protocol.p2p.ICommandDispatcher;
import com.muaythai.core.protocol.p2p.IClientConnection;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class LoginCommand implements ICommand {

    private String name;
    private String password;

    public LoginCommand(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void dispatch(ICommandDispatcher dispatcher, IClientConnection connection) {
        dispatcher.dispatch(this,connection);
    }
}

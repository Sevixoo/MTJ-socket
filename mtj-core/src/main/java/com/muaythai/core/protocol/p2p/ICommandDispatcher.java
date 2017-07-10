package com.muaythai.core.protocol.p2p;

import com.muaythai.core.login.LoginCommand;
import com.muaythai.core.login.SendMessageCommand;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface ICommandDispatcher {

    void onConnected(IClientConnection connection);

    void onDisconnected(IClientConnection connection);

    void onDestroyed();

    void onCreated();

    void dispatch(LoginCommand loginCommand, IClientConnection connection);

    void onMessageCommand(SendMessageCommand sendMessageCommand, IClientConnection sender);
}

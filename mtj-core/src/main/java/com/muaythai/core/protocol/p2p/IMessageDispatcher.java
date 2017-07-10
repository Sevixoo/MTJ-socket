package com.muaythai.core.protocol.p2p;

import com.muaythai.core.login.LoginSuccessMessage;
import com.muaythai.core.login.WelcomeMessage;

import java.io.Serializable;

/**
 * Created by pi19124 on 09.06.2017.
 */

public interface IMessageDispatcher extends Serializable {

    void onConnected(IServerConnection serverConnection);

    void onDisconnected(IServerConnection serverConnection);

    void onConnectError(Throwable throwable);

    void dispatch(LoginSuccessMessage loginSuccessMessage, IServerConnection connection);

    void onWelcomeMessage(WelcomeMessage welcomeMessage, IServerConnection connection);
}

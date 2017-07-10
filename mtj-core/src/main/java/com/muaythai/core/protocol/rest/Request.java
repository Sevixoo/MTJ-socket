package com.muaythai.core.protocol.rest;

import java.io.Serializable;

/**
 * Created by pi19124 on 14.06.2017.
 */

public class Request implements Serializable{

    private IRequest request;

    public Request(IRequest request) {
        this.request = request;
    }

    public IRequest getRequest() {
        return request;
    }
}

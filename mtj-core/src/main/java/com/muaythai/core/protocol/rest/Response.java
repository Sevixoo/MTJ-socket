package com.muaythai.core.protocol.rest;

import java.io.Serializable;

/**
 * Created by pi19124 on 14.06.2017.
 */

public class Response implements Serializable{

    public static final int RESULT_OK = 200;
    public static final int RESULT_NOT_FOUND = 404;

    private int resultCode;
    private ResponseHeader header;
    private IResponse body;

    public Response(IResponse body, int resultCode) {
        this.resultCode = resultCode;
        this.body = body;
    }

    public Response(int resultCode) {
        this.resultCode = resultCode;
        this.body = null;
    }
}

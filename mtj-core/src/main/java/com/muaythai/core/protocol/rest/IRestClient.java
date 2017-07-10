package com.muaythai.core.protocol.rest;

/**
 * Created by pi19124 on 12.06.2017.
 */

public interface IRestClient {

    <D extends IResponse> D request( IRequest request );

}

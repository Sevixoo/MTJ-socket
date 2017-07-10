package com.muaythai.core.protocol.rest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class REST {

    public static  <T> T create( final Class<T> service ,final IRestClient mRestClient ){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
                new InvocationHandler() {
                    @Override public Object invoke(Object proxy, Method method,  Object[] args) throws Throwable {
                        // If the method is a method from Object then defer to normal invocation.
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(this, args);
                        }
                        return mRestClient.request( (IRequest) args[0] );
                    }
                });
    }

}

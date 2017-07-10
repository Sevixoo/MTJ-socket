package com.muaythai.core.common;

/**
 * Created by pi19124 on 12.06.2017.
 */

public interface UseCase<RESULT,REQUEST> {

    RESULT execute( REQUEST request )throws Exception;

}

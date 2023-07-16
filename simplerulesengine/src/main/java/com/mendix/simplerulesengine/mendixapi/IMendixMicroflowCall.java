package com.mendix.simplerulesengine.mendixapi;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

public interface IMendixMicroflowCall
{
    void call(String mfName, Object mfParam) throws MendixAPIExecutionException;
}

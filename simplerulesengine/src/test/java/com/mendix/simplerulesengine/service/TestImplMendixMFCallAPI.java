package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;
import com.mendix.simplerulesengine.mendixapi.IMendixMicroflowCall;

public class TestImplMendixMFCallAPI implements IMendixMicroflowCall {
    @Override
    public void call(String mfName, Object mfParam) throws MendixAPIExecutionException {
        System.out.printf("Calling MF %s%n", mfName);
    }
}

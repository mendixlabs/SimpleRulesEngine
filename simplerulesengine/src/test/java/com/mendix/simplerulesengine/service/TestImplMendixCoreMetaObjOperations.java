package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.mendixapi.IMendixCoreMetaObjectOperations;

public class TestImplMendixCoreMetaObjOperations implements IMendixCoreMetaObjectOperations {
    @Override
    public Object getValue(Object input, String memberName) {
        System.out.printf("Calling getValue on member name and returning null value %s%n", memberName);
        return null;
    }

    @Override
    public void setValue(Object input, String memberName, Object newValue) {
        System.out.printf("Calling setValue on member name %s%n", memberName);
    }
}

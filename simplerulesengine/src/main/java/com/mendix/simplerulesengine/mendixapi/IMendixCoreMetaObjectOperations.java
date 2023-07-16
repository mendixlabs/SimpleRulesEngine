package com.mendix.simplerulesengine.mendixapi;

public interface IMendixCoreMetaObjectOperations
{
    Object getValue(Object input, String memberName);
    void setValue(Object input, String memberName, Object newValue);
}

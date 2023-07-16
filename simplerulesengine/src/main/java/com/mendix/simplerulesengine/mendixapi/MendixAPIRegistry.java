package com.mendix.simplerulesengine.mendixapi;

public final class MendixAPIRegistry
{
    private MendixAPIRegistry(){}
    public static IMendixCoreLogger mendixCoreLogger;
    public static IMendixCoreMetaObjectOperations mendixCoreMetaObjectOperations;
    public static IMendixMicroflowCall mendixMicroflowCall;
    public static void setMendixCoreLogger(IMendixCoreLogger coreLogger) {
        mendixCoreLogger = coreLogger;
    }
    public static void setMendixCoreMetaObjectOperator(IMendixCoreMetaObjectOperations coreMetaObjectOperations) {
        mendixCoreMetaObjectOperations = coreMetaObjectOperations;
    }
    public static void setMendixMicroflowCaller(IMendixMicroflowCall mendixMicroflowCaller) {
        mendixMicroflowCall = mendixMicroflowCaller;
    }
}

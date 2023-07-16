package com.mendix.simplerulesengine.mendixapi;

public interface IMendixCoreLogger
{
    void info(String message);

    void trace(String message);
    void debug(String message);
    void error(String message, Throwable cause);
}

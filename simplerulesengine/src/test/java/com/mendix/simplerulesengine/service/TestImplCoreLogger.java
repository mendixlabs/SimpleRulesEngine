package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.mendixapi.IMendixCoreLogger;

public class TestImplCoreLogger implements IMendixCoreLogger {
    @Override
    public void info(String message) {
        System.out.printf("Info: %s%n", message);
    }

    @Override
    public void trace(String message) {
        System.out.printf("Trace: %s%n", message);
    }

    @Override
    public void debug(String message) {
        System.out.printf("Debug: %s%n", message);
    }

    @Override
    public void error(String message, Throwable cause) {
        System.out.printf("Error: %s%n", message);
    }
}

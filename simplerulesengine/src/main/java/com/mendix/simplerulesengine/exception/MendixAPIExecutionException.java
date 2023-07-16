package com.mendix.simplerulesengine.exception;

public class MendixAPIExecutionException extends Exception
{
    public MendixAPIExecutionException()
    {
        super();
    }

    public MendixAPIExecutionException(String message)
    {
        super(message);
    }

    public MendixAPIExecutionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

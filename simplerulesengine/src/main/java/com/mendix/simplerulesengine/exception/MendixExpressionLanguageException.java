package com.mendix.simplerulesengine.exception;

public class MendixExpressionLanguageException extends Exception
{
    public MendixExpressionLanguageException()
    {
        super();
    }

    public MendixExpressionLanguageException(String message)
    {
        super(message);
    }

    public MendixExpressionLanguageException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

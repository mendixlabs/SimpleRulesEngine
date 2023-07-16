package com.mendix.simplerulesengine.exception;

public class RuleEngineException extends Exception {
    public RuleEngineException()
    {
        super();
    }

    public RuleEngineException(String message)
    {
        super(message);
    }

    public RuleEngineException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

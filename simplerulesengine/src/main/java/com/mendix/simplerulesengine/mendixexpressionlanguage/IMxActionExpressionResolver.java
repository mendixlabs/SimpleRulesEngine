package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

public interface IMxActionExpressionResolver
{
    static final String EXPRESSIONKEYWORD = "mx";

    String perfromAction(String expression, Object data) throws MendixAPIExecutionException;
}

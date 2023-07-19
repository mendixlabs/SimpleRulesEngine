package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

public interface IMxActionExpressionResolver
{
    String perfromAction(String expression, Object inputData, Object outputData) throws MendixAPIExecutionException;
}

package com.mendix.simplerulesengine.mendixexpressionlanguage;


import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

public interface IMxConditionExpressionResolver
{
    String resolve(String expression, Object data) throws MendixAPIExecutionException;
}

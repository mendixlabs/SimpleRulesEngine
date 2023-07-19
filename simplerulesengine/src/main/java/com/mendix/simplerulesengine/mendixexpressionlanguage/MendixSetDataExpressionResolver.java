package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry.mendixCoreMetaObjectOperations;

public class MendixSetDataExpressionResolver implements IMxActionExpressionResolver
{
    protected static final String RESOLVER_KEY = "setVal";
    public static final Pattern EXPRESSIONPATTERN = Pattern.compile(String.format("\\$\\(%s\\.%s\\.(input|output)\\.([a-zA-Z0-9_]+)\\s*=(.+?)\\)\\s*;?", ExpressionResolverService.EXPRESSIONKEYWORD, RESOLVER_KEY));
    @Override
    public String perfromAction(String expression, Object inputData, Object outputData) throws MendixAPIExecutionException
    {
        Matcher matcher = EXPRESSIONPATTERN.matcher(expression);
        while (matcher.find())
        {
            if(matcher.group(1).equals("input"))
            {
                mendixCoreMetaObjectOperations.setValue(inputData, matcher.group(2), matcher.group(3).trim());
            }
            if(matcher.group(1).equals("output"))
            {
                mendixCoreMetaObjectOperations.setValue(outputData, matcher.group(2), matcher.group(3).trim());
            }
            expression = expression.replace(matcher.group(0), "");
        }
        return expression;
    }
}

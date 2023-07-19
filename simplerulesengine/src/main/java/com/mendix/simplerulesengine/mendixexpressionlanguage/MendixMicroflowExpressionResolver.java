package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;
import com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MendixMicroflowExpressionResolver implements IMxActionExpressionResolver
{
    protected static final String RESOLVER_KEY = "callMF";
    public static final Pattern EXPRESSIONPATTERN = Pattern.compile(String.format("\\$\\(%s\\.%s\\.([a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+)\\)\\s*;?", ExpressionResolverService.EXPRESSIONKEYWORD, RESOLVER_KEY));

    @Override
    public String perfromAction(String expression, Object inputData, Object outputData) throws MendixAPIExecutionException
    {
        Matcher matcher = EXPRESSIONPATTERN.matcher(expression);
        while (matcher.find())
        {
            MendixAPIRegistry.mendixMicroflowCall.call(matcher.group(1), inputData);
            expression = expression.replace(matcher.group(0), "");
        }
        return expression;
    }
}

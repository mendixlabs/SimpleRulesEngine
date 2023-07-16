package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry.mendixCoreMetaObjectOperations;

public class MendixSetDataExpressionResolver implements IMxActionExpressionResolver
{
    protected static final String RESOLVER_KEY = "setVal";
    public static final Pattern EXPRESSIONPATTERN = Pattern.compile(String.format("\\$\\(%s\\.%s\\.input\\.([a-zA-Z0-9_]+)\\s*=(.+)\\)\\s*;?", IMxConditionExpressionResolver.EXPRESSIONKEYWORD, RESOLVER_KEY));
    @Override
    public String perfromAction(String expression, Object data) throws MendixAPIExecutionException
    {
        Matcher matcher = EXPRESSIONPATTERN.matcher(expression);
        while (matcher.find())
        {
            mendixCoreMetaObjectOperations.setValue(data, matcher.group(1), matcher.group(2).trim());
            expression = expression.replace(matcher.group(0), "");
        }
        return expression;
    }
}

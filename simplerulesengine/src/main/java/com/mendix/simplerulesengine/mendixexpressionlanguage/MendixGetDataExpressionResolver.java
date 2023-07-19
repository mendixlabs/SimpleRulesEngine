package com.mendix.simplerulesengine.mendixexpressionlanguage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry.mendixCoreMetaObjectOperations;

public class MendixGetDataExpressionResolver implements IMxConditionExpressionResolver
{
    protected static final String RESOLVER_KEY = "getVal";
    public static final Pattern EXPRESSIONPATTERN = Pattern.compile(String.format("\\$\\(%s\\.%s\\.input\\.([a-zA-Z0-9_]+)\\)", ExpressionResolverService.EXPRESSIONKEYWORD, RESOLVER_KEY));
    @Override
    public String resolve(String expression, Object input)
    {
        Matcher matcher = EXPRESSIONPATTERN.matcher(expression);
        while (matcher.find())
        {
            var val = mendixCoreMetaObjectOperations.getValue(input, matcher.group(1));
            if(val instanceof String)
                val = "\"" + val + "\"";
            expression = expression.replace(matcher.group(0), val == null ? "null" : val.toString());
        }
        return expression;
    }
}

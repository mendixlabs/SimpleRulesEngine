package com.mendix.simplerulesengine.mendixexpressionlanguage;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionResolverService
{
    private ExpressionResolverService(){}
    public static final Pattern EXPRESSIONPATTERN = Pattern.compile(String.format("(\\$\\(%s\\.([a-zA-Z]+)\\.[a-zA-Z0-9_.=\\s]+\\)\\s*;?)", IMxConditionExpressionResolver.EXPRESSIONKEYWORD));
    public static String resolveCondition(String expression, Object input) throws MendixAPIExecutionException {
        Matcher matcher = EXPRESSIONPATTERN.matcher(expression);
        while (matcher.find())
        {
            IMxConditionExpressionResolver mxExpressionResolver = null;
            //TODO: Implement factory/strategy pattern for this
            switch (matcher.group(2))
            {
                case MendixGetDataExpressionResolver.RESOLVER_KEY:
                    mxExpressionResolver = new MendixGetDataExpressionResolver();
                    break;
            }
            if(mxExpressionResolver != null)
            {
                var resolvedExpression = mxExpressionResolver.resolve(matcher.group(0), input);
                expression = expression.replace(matcher.group(0), resolvedExpression);
            }
        }
        return expression;
    }

    public static String resolveAction(String action, Object input) throws MendixAPIExecutionException {
        Matcher matcher = EXPRESSIONPATTERN.matcher(action);
        while (matcher.find())
        {
            IMxActionExpressionResolver mxActionExpressionResolver = null;
            //TODO: Implement factory/strategy pattern for this
            switch (matcher.group(2))
            {
                case MendixMicroflowExpressionResolver.RESOLVER_KEY:
                    mxActionExpressionResolver = new MendixMicroflowExpressionResolver();
                    break;
                case MendixSetDataExpressionResolver.RESOLVER_KEY:
                    mxActionExpressionResolver = new MendixSetDataExpressionResolver();
                    break;
            }
            if(mxActionExpressionResolver != null)
            {
                var resolvedAction = mxActionExpressionResolver.perfromAction(matcher.group(0), input);
                action = action.replace(matcher.group(0), resolvedAction);
            }
        }
        return action;
    }
}

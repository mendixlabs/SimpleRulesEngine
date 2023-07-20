package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.exception.RuleEngineException;
import com.mendix.simplerulesengine.mendixexpressionlanguage.ExpressionResolverService;
import com.mendix.simplerulesengine.model.Rule;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.Comparator;
import java.util.TreeSet;

import static com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry.mendixCoreLogger;

public class RuleEngine {
    private TreeSet<Rule> rules = null;

    public void addRule(Rule rule) throws RuleEngineException {
        if(rule == null)
            throw new RuleEngineException("rule cannot be null");

        if(rules == null)
            rules = new TreeSet<>(Comparator.comparingInt(Rule::getSequence));

        if(!rules.isEmpty() && rules.stream().anyMatch(r -> r.getSequence() == rule.getSequence()))
        {
            mendixCoreLogger.warn(String.format("Unable to add rule %s because the sequence number of this rule is duplicate within this namespace", rule.getId()));
            return;
        }

        if(!rules.isEmpty() && rules.stream().anyMatch(r -> r.getNamespace().equals(rule.getNamespace()) && r.getId() == rule.getId()))
            throw new RuleEngineException("rule is already added");

        rules.add(rule);
        mendixCoreLogger.debug(String.format("Rule ID is %s added into the engine.", rule.getId()));
    }

    public void execute(Object inputData, Object outputData) throws RuleEngineException {

        if(rules == null || rules.isEmpty())
            throw new RuleEngineException("Please add rules before executing the engine.");

        mendixCoreLogger.debug(String.format("Executing engine with %s rules", rules.size()));

        for (var rule : rules)
        {

            mendixCoreLogger.debug(String.format("Evaluating condition for rule id %s", rule.getId()));
            if(evaluateRuleCondition(rule.getCondition(), inputData))
            {
                mendixCoreLogger.debug(String.format("Executing action for rule id %s", rule.getId()));
                executeRuleAction(rule.getAction(), inputData, outputData);
                mendixCoreLogger.debug(String.format("Action executed for rule id %s", rule.getId()));
            }
            else
            {
                mendixCoreLogger.debug(String.format("Skipping rule %s because condition is not satisfied", rule.getId()));
            }
        }
    }

    private boolean evaluateRuleCondition(String condition, Object inputData) throws RuleEngineException {
        try
        {
            condition = ExpressionResolverService.resolveCondition(condition, inputData);
            VariableResolverFactory resolverFactory = new MapVariableResolverFactory();
            resolverFactory.createVariable("input", inputData);
            return MVEL.evalToBoolean(condition, resolverFactory);
        }
        catch (Exception ex)
        {
            throw new RuleEngineException("Exception occurred while evaluating condition", ex);
        }
    }

    private void executeRuleAction(String action, Object inputData, Object outputData) throws RuleEngineException {
        try
        {
            VariableResolverFactory resolverFactory = new MapVariableResolverFactory();
            resolverFactory.createVariable("input", inputData);
            resolverFactory.createVariable("output", outputData);
            action = ExpressionResolverService.resolveAction(action, inputData, outputData);
            MVEL.eval(action, resolverFactory);
        }
        catch (Exception ex) {
            throw new RuleEngineException("Exception occurred while executing action", ex);
        }
    }
}

package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.exception.RuleEngineException;
import com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry;
import com.mendix.simplerulesengine.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RuleEngineTest {

    @BeforeEach
    void RegisterAPIs()
    {
        MendixAPIRegistry.setMendixCoreLogger(new TestImplCoreLogger());
    }
    @Test
    @DisplayName("ThrowErrorIfRuleIsNull")
    void RuleNullCheck() {
        var ruleEngine = new RuleEngine();
        assertThrows(RuleEngineException.class, () -> ruleEngine.addRule(null), "rule cannot be null");
    }

    @Test
    @DisplayName("ThrowErrorWithoutRuleAdded")
    void ExecuteEngineWithoutRules() {
        assertThrows(RuleEngineException.class, () -> new RuleEngine().execute(null, null), "Please add rules before executing the engine.");
    }

    @ParameterizedTest
    @DisplayName("AddCorrectRules")
    @MethodSource("getCorrectRules")
    void RuleAdd(List<Rule> rules) {
        var ruleEngine = new RuleEngine();
        assertDoesNotThrow(() ->
        {
            for(var rule : rules)
            {
                ruleEngine.addRule(rule);
            }
        });
    }

    @ParameterizedTest
    @DisplayName("ThrowErrorIfDuplicateRuleFound")
    @MethodSource("getDuplicateRules")
    void DuplicateRuleAdd(List<Rule> rules) {
        var ruleEngine = new RuleEngine();
        assertThrows(RuleEngineException.class,() ->
        {
            for(var rule : rules)
            {
                ruleEngine.addRule(rule);
            }
        }, "rule is already added");
    }

    @ParameterizedTest
    @DisplayName("ExecuteEngineWithCorrectRule")
    @MethodSource("getCorrectRulesWithInputData")
    void ExecuteEngineWithCorrectRules(List<Rule> rules, Candidate candidate) {
        var ruleEngine = new RuleEngine();
        assertDoesNotThrow(() ->
        {
            for(var rule : rules)
            {
                ruleEngine.addRule(rule);
            }
            ruleEngine.execute(candidate, null);
            if(candidate.getName().equals("a"))
            {
                System.out.println("For candidate a");
                assertTrue(candidate.isPassed());
                assertEquals(2, candidate.getExamAttempt());
            }
            if(candidate.getName().equals("b"))
            {
                System.out.println("For candidate b");
                assertTrue(candidate.isPassed());
                assertEquals(3, candidate.getExamAttempt());
                assertEquals(35, candidate.getPercentage());
            }
            if(candidate.getName().equals("c"))
            {
                System.out.println("For candidate c");
                assertFalse(candidate.isPassed());
                assertEquals(4, candidate.getExamAttempt());
            }
        });
    }

    static Stream<Arguments> getCorrectRulesWithInputData() {
        Candidate candidate1 = new Candidate("a", false, 53, 1);
        Candidate candidate2 = new Candidate("b", false, 32, 2);
        Candidate candidate3 = new Candidate("c", false, 31, 3);
        var rule1 = new Rule();
        rule1.setNamespace("engine.test");
        rule1.setId(1);
        rule1.setName("Passing percentage");
        rule1.setSequence(1);
        rule1.setCondition("input.percentage >= 35");
        rule1.setAction("input.setPassed(true); input.setExamAttempt(input.examAttempt+1)");

        var rule2 = new Rule();
        rule2.setNamespace("engine.test");
        rule2.setId(2);
        rule2.setName("Apply grace mark and pass");
        rule2.setSequence(2);
        rule2.setCondition("input.percentage > 31 && input.percentage < 35 ");
        rule2.setAction("input.setPassed(true); input.setPercentage(35); input.setExamAttempt(input.examAttempt+1); input.setGraceMarkGiven(true)");

        var rule3 = new Rule();
        rule3.setNamespace("engine.test");
        rule3.setId(3);
        rule3.setName("Apply grace mark and pass");
        rule3.setSequence(3);
        rule3.setCondition("input.percentage <= 31 ");
        rule3.setAction("input.setPassed(false); input.setExamAttempt(input.examAttempt+1);");

        return Stream.of(
                arguments(List.of(rule1), candidate1),
                arguments(List.of(rule1, rule2, rule3), candidate2),
                arguments(List.of(rule1, rule2, rule3), candidate3)
        );
    }
    static Stream<Arguments> getCorrectRules() {
        var rule1 = new Rule();
        rule1.setNamespace("engine.test");
        rule1.setId(1);
        rule1.setName("rule1");
        rule1.setSequence(1);
        rule1.setCondition("input.percentage > 35");
        rule1.setAction("input.isPassed=true;");

        var rule2 = new Rule();
        rule2.setNamespace("engine.test");
        rule2.setId(2);
        rule2.setName("rule2");
        rule2.setSequence(2);
        rule2.setCondition("input.percentage < 35");
        rule2.setAction("input.isPassed=false; input.examAttempt++");

        return Stream.of(
                arguments(List.of(rule1)),
                arguments(List.of(rule1, rule2))
                );
    }

    static Stream<Arguments> getDuplicateRules() {
        var rule1 = new Rule();
        rule1.setNamespace("engine.test");
        rule1.setId(1);
        rule1.setName("rule1");
        rule1.setSequence(1);
        rule1.setCondition("1==1");
        rule1.setAction("System.out.println(\"Rule 1 action is executed.\")");

        var rule2 = new Rule();
        rule2.setNamespace("engine.test");
        rule2.setId(1);
        rule2.setName("rule2");
        rule2.setSequence(2);
        rule2.setCondition("1==1");
        rule2.setAction("System.out.println(\"Rule 2 action is executed.\")");

        return Stream.of(
                arguments(List.of(rule1, rule2))
        );
    }
}
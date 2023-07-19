package com.mendix.simplerulesengine.service;

import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;
import com.mendix.simplerulesengine.mendixapi.MendixAPIRegistry;
import com.mendix.simplerulesengine.mendixexpressionlanguage.ExpressionResolverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
class MendixExpressionLanguageTests extends ExpressionLangTestData
{
    @BeforeEach
    void RegisterMendixAPIs()
    {
        MendixAPIRegistry.setMendixCoreLogger(new TestImplCoreLogger());
        MendixAPIRegistry.setMendixMicroflowCaller(new TestImplMendixMFCallAPI());
        MendixAPIRegistry.setMendixCoreMetaObjectOperator(new TestImplMendixCoreMetaObjOperations());
    }

    @ParameterizedTest(name = "Action {index}")
    @DisplayName("Check MX expression lang for actions")
    @MethodSource("Actions")
    void CheckMXExpressionLangForActions(String action, String expected) throws MendixAPIExecutionException {
        System.out.printf("action: %s%n", action);
        action = ExpressionResolverService.resolveAction(action, null, null);
        assertEquals(expected, action);
    }

    @ParameterizedTest(name = "Condition {index}")
    @DisplayName("Check MX expression lang for conditions")
    @MethodSource("Conditions")
    void CheckMXExpressionLangForConditions(String condition, String expected) throws MendixAPIExecutionException {
        System.out.printf("condition: %s%n", condition);
        condition = ExpressionResolverService.resolveCondition(condition, null);
        assertEquals(expected, condition);
    }
}

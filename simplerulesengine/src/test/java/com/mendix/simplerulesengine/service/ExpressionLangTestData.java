package com.mendix.simplerulesengine.service;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ExpressionLangTestData
{
    protected static Stream<Arguments> Actions() {
        return Stream.of(
                Arguments.of("System.out.println(\"Mendix\");", "System.out.println(\"Mendix\");"),
                Arguments.of("$(mx.callMF.TestModule.DummyMF);", ""),
                Arguments.of("$(mx.setVal.input.DummyAttr_1=2);", ""),
                Arguments.of("$(mx.setVal.input.DummyAttr_1=2)", ""),
                Arguments.of("$(mx.setVal.input.DummyAttr_1                        =           2)             ;", ""),
                Arguments.of("$(mx.callMF.TestModule.DummyMF); $(mx.callMF.TestModule.DummyMF_1);", " "),
                Arguments.of("$(mx.callMF.TestModule.DummyMF); System.out.println(\"Mendix\"); $(mx.callMF.TestModule.DummyMF_1)", " System.out.println(\"Mendix\"); "),
                Arguments.of("$(mx.callMF.TestModule.DummyMF); System.out.println(\"Mendix\"); $(mx.callMF.TestModule.DummyMF_1); $(mx.setVal.input.DummyAttr_1=2);", " System.out.println(\"Mendix\");  ")
        );
    }

    protected static Stream<Arguments> Conditions() {
        return Stream.of(
                Arguments.of("System.out.println(\"Mendix\");", "System.out.println(\"Mendix\");"),
                Arguments.of("$(mx.getVal.input.DummyAttr_1);", "null;"),
                Arguments.of("$(mx.getVal.input.DummyAttr_1) > 1", "null > 1"),
                Arguments.of("$(mx.getVal.input.DummyAttr_1) > 1 && $(mx.getVal.input.DummyAttr_2) > 2", "null > 1 && null > 2")

        );
    }
}

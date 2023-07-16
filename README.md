[![Known Vulnerabilities](https://snyk.io/test/github/mendixlabs/SimpleRulesEngine/badge.svg?targetFile=simplerulesengine/build.gradle&tab=dependencies)](https://snyk.io/test/github/mendixlabs/SimpleRulesEngine?targetFile=simplerulesengine/build.gradle&tab=dependencies)
[![Support](https://img.shields.io/badge/Mendix%20Support%3A-Community-green.svg)](https://docs.mendix.com/community/app-store/app-store-content-support)
## What is Rules Engine and it's benefit?
To put it in simple term, it's an expert program which runs the conditions (viz. rules) on the data and if any condition (viz. a rule) matches then it executes the corresponding actions.

So in terms of business, think of business rules as “if-then” statements. So, a basic example of a rule would be, “If A, then B, else if X, then do Y.”

I see couple of major benefits of rules engine:

1. It enables users to modify business logic without touching the application's code.

2. Non-technical users can manage critical processes/logic without having to plow through hundreds of thousands of lines of code.

## Simple Rules Engine
Simple Rule Engine is the simple, stupid rules engine. It provides the facility to define the  `Rule` with conditions and actions, and the `RulesEngine` evaluate conditions and execute actions.

Surprisingly by using a simple Mendix Expression Language this rule engine also supports defining conditions on Mendix object, as well as you can define actions on Mendix objects and even execute a Microflow.

## Features
- Define rules on Mendix objects by using Mendix Expression language
- Create namespace and define and set sequence of multiple rules
- Each rule will have it's corresponding action
- Action be like setting value to input data or call a Microflow
- Rule engine is Mendix context aware, that means you can use it with NPE as well
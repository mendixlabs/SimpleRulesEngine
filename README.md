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
- Define rules on Mendix objects by using [Mendix Expression Language](https://github.com/mendixlabs/SimpleRulesEngine/wiki/Mendix-Expression-Language) (MEL)
- Ability to define namespace to hold set rules together 
- Each rule will have it's corresponding action and that will be executed when condition is satisfied
- By using Mendix Expression Language (MEL) in Action we can modify (set) value of an attribute of input or output object
- As an Action you can also call a Microflow
- Rules engine is Mendix context aware, that means you can use it with NPE as well

## How to configure Simple Rules Engine in your app?
- Download the module in your Mendix App
- Create a navigation item or open overview page using "SimpleRuleEngine_Overview" page from `__UseMe` folder. As a alternative you can also use "SNIP_SimpleRulesEngine_Overview" snippet in your page.

# How to define rules in Simple Rules Engine?

## 1. Define Namespace:
Namespace is nothing but keeping same nature of rules together. A namespace can have many rules with unique sequence number. Namespace name should be unique with an app. To execute `RuleEngine` you have to fetch the namespace from DB and give it to `RuleEngine`, rule engine will fetch all rules defined within that namespace and execute it one after another.

## 2. Define Rules with a Namespace:
Once you defined a Namespace now you can create rules within it. Rules have the following properties:

- Name (required): any string name to identify the rule in a better way.
- Description (optional): any string which will describe about the rule.
- Sequence (required): a number. This should be unique within the selected namespace. Within a namespace multiple rules can not have same sequence number, and if duplication in sequence number is found then only first rule will be considered by Rules Engine.
- Condition (required): set of conditions that should be satisfied in order to apply the rule (or to execute action defined). You can also use [Mendix Expression Language](https://github.com/mendixlabs/SimpleRulesEngine/wiki/Mendix-Expression-Language) (MEL to define conditions by using Mendix object passed as input to `RuleEngine`
- Action (required): set of actions to perform when conditions are satisfied. By using [Mendix Expression Language](https://github.com/mendixlabs/SimpleRulesEngine/wiki/Mendix-Expression-Language) (MEL) we can modify the input/output Mendix objects and can also call a Microflow.

# How to execute `RulesEngine` by using defined rules?
To execute rules you can use "RulesExecutor" JavaAction from `__UseMe` folder of the module. This JavaAction takes 3 inputs:

- RulesNamespace (required): A namespace have set of rules defined, to execute those rules you have to fetch and pass `RulesNamespace` object and engine will fetch all the rules defined within it. 
- InputData (optional): An Mendix object as input to engine, on which rules conditions will be executed
- OutputData (optional): An Mendix object as output to engine, on which rules actions will be executed. By default, InputData object can also be used in actions.

This JavaAction returns nothing. `RulesEngine` will fetch all the rules defined within passed namespace, it will also sort all the rules by Sequence number. Keeping lower sequence number at first to execute and so on. If multiple rules shares the same sequence number than only the first rule of the same sequence number will be considered and others will be skipped (a warning message will be seen in console if such case happens). 

## 1. How `RulesEngine` executes the rules?
As this is a simple, stupid rules engine, so there is no complex logic written. Rules are executed in following manner:

- Rules are sorted by sequence number, the lowest sequence number will be the first and so on.
- Rules are always executed one after the another
- `Condition` defined in a rule is evaluated first, if it is satisfied then it's corresponding action is executed; else if condition is not satisfied then it's action won't be executed and moved to next rules if any.
- In case of any exception while evaluating `Condition` or executing `Action`, rule engine will throw an error and stop execution of any further rules. Though any modifications done on `InputData/OutputData` won't be rolled back.
- While executing `Action` or `Condition` on Mendix object current Mendix context will be used and if no context found (Is this even possible?) then system context will be created.

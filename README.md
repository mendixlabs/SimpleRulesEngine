[![Known Vulnerabilities](https://snyk.io/test/github/mendixlabs/SimpleRulesEngine/badge.svg?targetFile=simplerulesengine/build.gradle&tab=dependencies)](https://snyk.io/test/github/mendixlabs/SimpleRulesEngine?targetFile=simplerulesengine/build.gradle&tab=dependencies)
[![Support](https://img.shields.io/badge/Mendix%20Support%3A-Community-green.svg)](https://docs.mendix.com/community/app-store/app-store-content-support)
## What is Rules Engine and it's benefit?
To put it in simple term, it's an expert program which runs the conditions (viz. rules) on the data and if any condition (viz. a rule) matches then it executes the corresponding actions.

So in terms of business, think of business rules as “if-then” statements. So, a basic example of a rule would be, “If A, then B, else if X, then do Y.”

Couple of major benefits of rules engine:

1. It enables users to modify business logic without touching the application's code.

2. Non-technical users can manage critical processes/logic without having to go through hundreds of thousands of lines of code.

## Simple Rules Engine
Simple Rule Engine is the simple, stupid rules engine. It provides the facility to define the  `Rule` with conditions and actions, and the `RulesEngine` evaluate conditions and execute actions.

Surprisingly by using a simple Mendix Expression Language this rule engine also supports defining conditions on Mendix object, as well as you can define actions on Mendix objects and even execute a Microflow.

## Features
- Define rules on Mendix objects by using [Mendix Expression Language](https://github.com/mendixlabs/SimpleRulesEngine/wiki/Mendix-Expression-Language) (MEL)
- Ability to define namespace to hold set of rules together 
- Each rule will have it's corresponding action and that will be executed when condition is satisfied
- By using Mendix Expression Language (MEL) in Action we can modify (set) value of an attribute of input or output object
- As an Action you can also call a Microflow
- Rules engine is Mendix context aware, that means you can use it with NPE as well

## How to configure Simple Rules Engine in your app?
- Download the module in your Mendix App
- Create a navigation item using "SimpleRuleEngine_Overview" page from `__UseMe` folder. As an alternative you can also use "SNIP_SimpleRulesEngine_Overview" snippet in your page.

## Visit Github [Wiki](https://github.com/mendixlabs/SimpleRulesEngine/wiki) page for detailed documentation.
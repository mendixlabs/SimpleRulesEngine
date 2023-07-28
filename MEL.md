# Mendix Expression Language (MEL) 

To work with Mendix objects in rules, this module has developed Mendix Expression Language. By using this expression language you can:

- Get value of an attribute of passed `InputData` so that you can define a `Condition` based on the value
- Set value to an attribute of passed `InputData` or `OutputData`
- Call a Microflow to more things within Mendix
- And more support is coming...

### How to get value of an attribute?
It's simple, `$(mx.getVal.input.attributeName)`. This should be used in `Action` only.

For example if you have an entity named `Person` with one of the attribute as `Gender` (of type enum) and you have passed an object of this entity as `InputData` to the "RulesExecutor" JavaAction then you can write condition like `$(mx.getVal.input.Gender) == "Male"`. This condition will be satisfied if passed object has set `Gender` as `Male`.

### How to set value to an attribute?
It's simple again, `$(mx.setVal.input.attributeName = value)`. This should be used in `Condition` only.

Let's take the same example of `Person` entity and this time you want to set `Gender` to `Male` then your action should have `$(mx.setVal.input.Gender = Male)`.

If in case you want set value to `OutputData` then `$(mx.setVal.output.attributeName = value)` should be written

### How to call a Microflow?
Use `$(mx.callMF.moduleName.microflowName)`. This should be used in `Condition` only.

For example if you have a Microflow named as "MyFirstMicroflow" with module "MyFirstModule" then your expression should be `$(mx.callMF.MyFirstModule.MyFirstMicroflow)`.

The `InputData` object will always be passed to calling Microflow. Microflow should not return anything.

### Can we combine multiple conditions?
Yes, you can. Just separate each condition by a semicolon (;)

Like, `$(mx.setVal.input.Gender = Male); $(mx.callMF.MyFirstModule.MyFirstMicroflow);`. This action expression will set the value as well as call the specified Microflow. 
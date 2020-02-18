# LibGamerule

Libgamerule allows for easily adding custom gamerules.

```java
// Adding a boolean gamerule

GameRules.RuleKey<BooleanRule> MY_GAMERULE = Gamerule.register(
    "myGameRule",               // name in /gamerule
    BooleanRule.create(true)    // default to true
);

// Get the gamerule data
boolean value = Gamerule.get(MY_GAMERULE);
```

Supported Rule types:

- BooleanRule
- DoubleRule (+min/max)
- IntRule (+min/max)
- EnumRule (Any Enum)
- StringRule (greedy strings)

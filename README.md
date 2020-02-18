# LibGamerule

Libgamerule allows for easily adding custom gamerules.


### Usage
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

### Libcd support

#### Conditions 
*(note: does not support enums)*
```json
{
    "when": [
        { 
            "comment": "Boolean gamerule check",
            "libgamerule:gamerule_has_value": "my_gamerule" 
        },
        { 
            "comment": "Typed gamerule check",
            "libgamerule:gamerule_has_value": {
                "name": "my_gamerule_typed",
                "type": "int",
                "value": 10
            } 
        }
    ]
}
```

#### Creating gamerules in scripts
```js
var GameruleTweaker = libcd.require("libgamerule.GameruleTweaker");
GameruleTweaker.addGamerule("my_gamerule", "boolean", false);
GameruleTweaker.addGamerule("my_gamerule_typed", "integer", 7);
```

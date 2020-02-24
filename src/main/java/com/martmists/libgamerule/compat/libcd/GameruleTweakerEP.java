package com.martmists.libgamerule.compat.libcd;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.rules.BooleanRule;
import com.martmists.libgamerule.rules.DoubleRule;
import com.martmists.libgamerule.rules.IntRule;
import com.martmists.libgamerule.rules.StringRule;
import io.github.cottonmc.libcd.api.CDSyntaxError;
import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import io.github.cottonmc.libcd.api.tweaker.TweakerManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class GameruleTweakerEP implements LibCDInitializer {
    public void initTweakers(TweakerManager p0) {
        p0.addAssistant("libgamerule.GameruleTweaker", GameruleTweaker.class);
    }

    @Override
    public void initConditions(ConditionManager conditionManager) {
        conditionManager.registerCondition(new Identifier("libgamerule:gamerule_has_value"), (value) -> {
            if (value instanceof String) {
                // assume booleanrule
                return Gamerule.get(new GameRules.RuleKey<BooleanRule>((String) value));
            } else if (value instanceof JsonObject) {
                JsonObject jo = (JsonObject) value;
                String ruleName = ((JsonPrimitive) jo.get("name")).asString();
                String ruleType = ((JsonPrimitive) jo.get("type")).asString();
                switch (ruleType) {
                    case "string":
                        return Gamerule.get(new GameRules.RuleKey<StringRule>(ruleName)).equals(((JsonPrimitive) jo.get("value")).asString());
                    case "double":
                        return Gamerule.get(new GameRules.RuleKey<DoubleRule>(ruleName)) == ((JsonPrimitive) jo.get("value")).asDouble(0.0);
                    case "int":
                    case "integer":
                        return Gamerule.get(new GameRules.RuleKey<IntRule>(ruleName)) == ((JsonPrimitive) jo.get("value")).asInt(0);
                    case "boolean":
                        return Gamerule.get(new GameRules.RuleKey<BooleanRule>(ruleName));
                    default:
                        throw new CDSyntaxError("Invalid rule type: " + ruleType);
                }
            }
            return false;
        });
    }
}

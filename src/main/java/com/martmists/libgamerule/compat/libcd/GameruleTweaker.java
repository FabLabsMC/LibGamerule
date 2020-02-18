package com.martmists.libgamerule.compat.libcd;

import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.rules.BooleanRule;
import com.martmists.libgamerule.rules.DoubleRule;
import com.martmists.libgamerule.rules.IntRule;
import com.martmists.libgamerule.rules.StringRule;
import net.minecraft.world.GameRules;

public class GameruleTweaker {
    public void addGamerule(String name, String type, Object defaultValue){
        try {
            GameRules.RuleType<?> t;
            switch (type) {
                case "int":
                case "integer":
                    t = IntRule.create((Integer) defaultValue);
                    break;
                case "bool":
                case "boolean":
                    t = BooleanRule.create((Boolean) defaultValue);
                    break;
                case "double":
                    t = DoubleRule.create((Double) defaultValue);
                    break;
                case "string":
                    t = StringRule.create((String) defaultValue);
                    break;
                case "enum":
                    throw new Exception("Enums are not yet supported in libcd scripts");
                default:
                    throw new Exception("Unknown rule type!");
            }
            Gamerule.register(name, t);
        } catch (Exception e) {
            System.out.println("Error parsing gamerule - " + e.getMessage());
        }
    }
}
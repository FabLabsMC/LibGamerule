package com.martmists.libgamerule.mixin;

import com.google.common.collect.ImmutableMap;
import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.entities.UpdateGameRules;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(GameRules.class)
public class GameRulesMixin implements UpdateGameRules {
    @Final
    @Shadow
    private static Map<GameRules.RuleKey<?>, GameRules.RuleType<?>> RULE_TYPES;

    @Shadow
    private Map<GameRules.RuleKey<?>, GameRules.Rule<?>> rules;

    public void updateGamerules() {
        // In case new Gamerules were added late by e.g. libcd
        if (Gamerule.dirty) {
            Gamerule.dirty = false;
            this.rules = RULE_TYPES.entrySet()
                    .stream()
                    .collect(ImmutableMap.toImmutableMap(
                            Map.Entry::getKey,
                            (e) -> ((GameRules.RuleType<?>) e.getValue()).createRule()
                    ));
        }
    }
}

package com.martmists.libgamerule.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRuleCommand.class)
public interface GameRuleCommandAccessor {
    @Invoker
    static <T extends GameRules.Rule<T>> int invokeExecuteSet(CommandContext<ServerCommandSource> commandContext, GameRules.RuleKey<T> ruleKey) {
        throw new AssertionError("This shouldn't happen!");
    }

    @Invoker
    static <T extends GameRules.Rule<T>> int invokeExecuteQuery(ServerCommandSource serverCommandSource, GameRules.RuleKey<T> ruleKey) {
        throw new AssertionError("This shouldn't happen!");
    }
}

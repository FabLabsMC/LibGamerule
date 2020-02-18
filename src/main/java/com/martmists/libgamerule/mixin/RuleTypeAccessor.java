package com.martmists.libgamerule.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(GameRules.RuleType.class)
public interface RuleTypeAccessor {
    @Invoker("<init>")
    @SuppressWarnings("PublicStaticMixinMember")
    static GameRules.RuleType invokeNew(Supplier<ArgumentType<?>> argumentType, Function factory, BiConsumer notifier) {
        throw new AssertionError("This shouldn't happen!");
    }
}

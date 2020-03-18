package com.martmists.libgamerule.rules;

import com.martmists.libgamerule.Gamerule;
import com.martmists.libgamerule.arguments.EnumArgumentType;
import com.martmists.libgamerule.entities.ValueGetter;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class EnumRule<E extends Enum<E>> extends GameRules.Rule<EnumRule<E>> implements ValueGetter<E> {
    private Class<E> clazz;

    private E value;

    public EnumRule(GameRules.RuleType<EnumRule<E>> ruleType, E value) {
        super(ruleType);
        this.value = value;
        this.clazz = value.getDeclaringClass();
    }

    public static <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> create(E defaultValue) {
        return Gamerule.createRuleType(
                () -> new EnumArgumentType<>(defaultValue.getDeclaringClass()),
                (GameRules.RuleType<EnumRule<E>> t) -> new EnumRule<>(t, defaultValue)
        );
    }

    @Override
    protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
        value = context.getArgument(name, clazz);
    }

    @Override
    protected void deserialize(String string) {
        value = Enum.valueOf(clazz, string);
    }

    @Override
    protected String serialize() {
        return value.toString();
    }

    @Override
    public int getCommandResult() {
        return 0;
    }

    @Override
    protected EnumRule<E> getThis() {
        return this;
    }

    @Override
    public E get() {
        return value;
    }
}

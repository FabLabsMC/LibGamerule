package com.martmists.libgamerule.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.CommandSource;
import net.minecraft.text.LiteralText;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class EnumArgumentType<E extends Enum<E>> implements ArgumentType<E> {
    Class<E> clazz;

    public EnumArgumentType(Class<E> enumClass) {
        this.clazz = enumClass;
    }

    @Override
    public E parse(StringReader reader) throws CommandSyntaxException {
        StringBuilder param = new StringBuilder();

        if (reader.canRead() && Character.isJavaIdentifierStart(reader.peek())) {
            do {
                param.append(reader.read());
            } while (reader.canRead() && Character.isJavaIdentifierPart(reader.peek()));
        }

        try {
            return Enum.valueOf(clazz, param.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SimpleCommandExceptionType(new LiteralText("Invalid enum value!")).createWithContext(reader);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(
                Arrays.stream(clazz.getEnumConstants())
                        .map(Enum::toString)
                        .toArray(String[]::new),
                builder
        );
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.stream(clazz.getEnumConstants())
                .map(Enum::toString)
                .limit(2)
                .collect(Collectors.toList());
    }
}

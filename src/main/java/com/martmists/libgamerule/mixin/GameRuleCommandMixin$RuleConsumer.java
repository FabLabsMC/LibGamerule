package com.martmists.libgamerule.mixin;

import com.martmists.libgamerule.impl.LiteralRuleCommand;
import com.martmists.libgamerule.impl.LiteralRuleType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/server/command/GameRuleCommand$1")
public abstract class GameRuleCommandMixin$RuleConsumer {
	@Shadow
	private LiteralArgumentBuilder<ServerCommandSource> field_19419;

	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(at = @At("HEAD"), method = "accept(Lnet/minecraft/world/GameRules$RuleKey;Lnet/minecraft/world/GameRules$RuleType;)V", cancellable = true)
	private <T extends GameRules.Rule<T>> void lgb_onRegisterCommand(GameRules.RuleKey<T> key, GameRules.RuleType<T> type, CallbackInfo ci) {
		// Check if our type is a LiteralRuleType
		if (type instanceof LiteralRuleType) {
			LiteralRuleCommand.register(this.field_19419, key, (LiteralRuleType<T>) type);
			ci.cancel();
		}
	}
}

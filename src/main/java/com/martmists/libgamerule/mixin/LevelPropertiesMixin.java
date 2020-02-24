package com.martmists.libgamerule.mixin;

import com.martmists.libgamerule.entities.UpdateGameRules;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {
    @Shadow
    @Final
    private GameRules gameRules;

    @Inject(method = "getGameRules", at = @At("HEAD"))
    void preUpdate(CallbackInfoReturnable<GameRules> cir) {
        // We refresh the gamerules in case any were added after the world was initialized.
        ((UpdateGameRules) gameRules).updateGamerules();
    }
}

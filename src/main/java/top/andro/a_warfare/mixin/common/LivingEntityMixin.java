package top.andro.a_warfare.mixin.common;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.andro.a_warfare.Config;
import top.andro.a_warfare.entity.projectile.ProjectileEntity;

/**
 * Author: MrCrayfish
 */
@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private DamageSource scorched_Guns_1_20_1$currentDamageSource;

    @Inject(method = "hurt", at = @At("HEAD"))
    private void onHurtStart(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.scorched_Guns_1_20_1$currentDamageSource = source;
    }

    @ModifyArg(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V"), index = 0)
    private double modifyApplyKnockbackArgs(double original) {
        if (this.scorched_Guns_1_20_1$currentDamageSource != null && this.scorched_Guns_1_20_1$currentDamageSource.getDirectEntity() instanceof ProjectileEntity) {
            if (!Config.COMMON.gameplay.enableKnockback.get()) {
                return 0.0D;
            }
            double strength = Config.COMMON.gameplay.knockbackStrength.get();
            return strength > 0 ? strength : original;
        }
        return original;
    }
}
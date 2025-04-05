package berry.variant.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import berry.variant.VariantMode;
import net.minecraft.world.entity.LivingEntity;

@Mixin (LivingEntity.class)
public abstract class LivingEntityFix {
    @Redirect (method = "jumpFromGround", at = @At (value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"), remap = false)
    private double jump (double a, double b) {
        if (VariantMode.LOW_JUMP_ON_SLIME.isEnabled ()) return a;
        else return Math.max (a, b);
    }
    @Redirect (method = "aiStep", at = @At (value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"), remap = false)
    private boolean eqs (Object ths, Object obj) {
        if (VariantMode.LEGACY_INERTIA.isEnabled ()) return false;
        else return ths.equals (obj);
    }
    @ModifyConstant (method = "aiStep", constant = @Constant (doubleValue = 0.003), remap = false)
    private double inertia (double val) {
        return VariantMode.INERTIA.getValue ();
    }
}

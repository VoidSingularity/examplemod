package berry.test.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.LivingEntity;

@Mixin (LivingEntity.class)
public class LivingEntityFix {
    @Redirect (method = "jumpFromGround", at = @At (value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"), remap = false)
    private double jump (double a, double b) {
        return a;
    }
    @Redirect (method = "aiStep", at = @At (value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"), remap = false)
    private boolean eqs (Object ths, Object obj) {
        return false;
    }
}

package berry.variant.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableList;

import berry.variant.VariantMode;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Mixin (Entity.class)
public abstract class EntityFix {
    @Inject (method = "axisStepOrder", at = @At ("TAIL"), cancellable = true, remap = false)
    private static void inject (Vec3 arg, CallbackInfoReturnable <Iterable <Direction.Axis>> cir) {
        if (VariantMode.LEGACY_COLLISION.isEnabled ()) {
            cir.setReturnValue (getYXZ ());
        }
    }
    @Accessor (value = "YXZ_AXIS_ORDER", remap = false)
    private static ImmutableList <Direction.Axis> getYXZ () {
        throw new AssertionError ();
    }
}

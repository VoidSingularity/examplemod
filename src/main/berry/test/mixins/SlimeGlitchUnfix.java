package berry.test.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin (SlimeBlock.class)
public abstract class SlimeGlitchUnfix {
    @Inject (at = @At ("HEAD"), method = "fallOn", cancellable = true, remap = false)
    private void unfix (Level level, BlockState state, BlockPos pos, Entity entity, float f, CallbackInfo ci) {
        if (entity.isSuppressingBounce ()) {
            entity.causeFallDamage (f, 1.0f, level.damageSources () .fall ());
            ci.cancel ();
        }
    }
}

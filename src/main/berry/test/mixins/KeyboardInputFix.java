package berry.test.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.phys.Vec2;

@Mixin (KeyboardInput.class)
public class KeyboardInputFix {
    @Redirect (method = "tick", at = @At (value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec2;()Lnet/minecraft/world/phys/Vec2;"), remap = false)
    private Vec2 retorig (Vec2 orig) {
        return orig;
    }
}

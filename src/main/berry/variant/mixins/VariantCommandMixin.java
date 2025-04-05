package berry.variant.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.brigadier.arguments.StringArgumentType;

import berry.variant.VariantMode;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@Mixin (Commands.class)
public abstract class VariantCommandMixin {
    @Inject (at = @At ("TAIL"), method = "<init>", remap = false)
    private void register (CommandSelection selection, CommandBuildContext ctx, CallbackInfo ci) {
        var dispatcher = ((Commands) (Object) this) .getDispatcher ();
        dispatcher.register (
            Commands.literal ("variant")
            .then (
                Commands.argument ("id", ResourceLocationArgument.id ())
                .then (
                    Commands.argument ("value", StringArgumentType.greedyString ())
                    .executes ((css) -> {
                        ResourceLocation loc = ResourceLocationArgument.getId (css, "id");
                        VariantMode mode = VariantMode.getVariant (loc);
                        if (mode == null) {
                            css.getSource () .getPlayer () .sendSystemMessage (Component.literal ("MODE NOT FOUND") .withColor (0xff0000));
                            return 0;
                        }
                        String value = StringArgumentType.getString (css, "value");
                        if (mode.setValue (value)) {
                            css.getSource () .getPlayer () .sendSystemMessage (Component.literal ("Successfully set mode " + loc.toString () + " to " + value));
                            return 1;
                        } else {
                            css.getSource () .getPlayer () .sendSystemMessage (Component.literal ("INVALID VALUE: " + value + " with mode " + loc.toString ()) .withColor (0xff0000));
                            return 0;
                        }
                    })
                )
            )
        );
    }
}

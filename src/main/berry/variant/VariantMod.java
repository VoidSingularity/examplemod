package berry.variant;

import org.spongepowered.asm.mixin.Mixins;

import berry.loader.BerryModInitializer;
import berry.loader.JarContainer;
import berry.utils.Graph;

public class VariantMod implements BerryModInitializer {
    private static String modid;
    public static String getModID () {
        return modid;
    }
    @Override
    public void preinit (Graph G, JarContainer jar, String name) {
        modid = name;
        var v = new Graph.Vertex (name);
        G.addVertex (v);
        G.addEdge (null, G.getVertices () .get ("berrybuiltins"), v, null);
    }
    public void initialize (String[] argv) {
        Mixins.addConfiguration ("variantmode.json");
    }
}

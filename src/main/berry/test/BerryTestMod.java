package berry.test;

import org.spongepowered.asm.mixin.Mixins;

import berry.loader.BerryModInitializer;
import berry.loader.JarContainer;
import berry.utils.Graph;

public class BerryTestMod implements BerryModInitializer {
    @Override
    public void preinit (Graph G, JarContainer jar, String name) {
        var v = new Graph.Vertex (name);
        G.addVertex (v);
        G.addEdge (null, G.getVertices () .get ("berrybuiltins"), v, null);
    }
    public void initialize (String[] argv) {
        Mixins.addConfiguration ("examplemixin.json");
    }
}

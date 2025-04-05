package berry.variant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;

public abstract class VariantMode {
    private boolean registered;
    private final ResourceLocation id;
    public VariantMode (ResourceLocation location) {
        this.registered = false;
        this.id = location;
    }
    public ResourceLocation getID () {
        return this.id;
    }
    public abstract boolean setValue (String value);
    private static final Map <ResourceLocation, VariantMode> variants = new HashMap <> ();
    public void register () throws IllegalStateException {
        if (this.registered) throw new IllegalStateException ("Instance already registered!");
        else if (variants.containsKey (id)) throw new IllegalStateException ("ID already registered!");
        variants.put (this.id, this);
    }
    public static Collection <ResourceLocation> all () {
        return variants.keySet ();
    }
    public static VariantMode getVariant (ResourceLocation location) {
        return variants.get (location);
    }
    public static class BooleanVariant extends VariantMode {
        public BooleanVariant (ResourceLocation location) {
            super (location);
            this.enabled = false;
        }
        private boolean enabled;
        public boolean isEnabled () {
            return this.enabled;
        }
        public void setEnabled (boolean e) {
            this.enabled = e;
        }
        public void enable () {
            setEnabled (true);
        }
        public void disable () {
            setEnabled (false);
        }
        public boolean setValue (String value) {
            if (value.equalsIgnoreCase ("true")) this.enable ();
            else if (value.equalsIgnoreCase ("false")) this.disable ();
            else return false;
            return true;
        }
    }
    public static class DoubleVariant extends VariantMode {
        double d;
        public DoubleVariant (ResourceLocation location, double defaults) {
            super (location);
            this.d = defaults;
        }
        public void setValue (double v) {
            this.d = v;
        }
        public double getValue () {
            return this.d;
        }
        public boolean setValue (String value) {
            try {
                this.d = Double.valueOf (value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
    public static final BooleanVariant LEGACY_COLLISION;
    public static final BooleanVariant LOW_JUMP_ON_SLIME;
    public static final BooleanVariant SLIME_FALL_DAMAGE;
    public static final BooleanVariant LEGACY_INERTIA;
    public static final DoubleVariant INERTIA;
    private static BooleanVariant newbool (String name) {
        var mode = new BooleanVariant (ResourceLocation.fromNamespaceAndPath (VariantMod.getModID (), name));
        mode.register (); return mode;
    }
    static {
        try {
            LEGACY_COLLISION = newbool ("legacy_collision");
            LOW_JUMP_ON_SLIME = newbool ("low_jump_on_slime");
            SLIME_FALL_DAMAGE = newbool ("slime_fall_damage");
            LEGACY_INERTIA = newbool ("legacy_inertia");
            INERTIA = new DoubleVariant (ResourceLocation.fromNamespaceAndPath (VariantMod.getModID (), "inertia"), 0.003);
            INERTIA.register ();
        } catch (IllegalStateException e) {
            throw new RuntimeException (e);
        }
    }
}

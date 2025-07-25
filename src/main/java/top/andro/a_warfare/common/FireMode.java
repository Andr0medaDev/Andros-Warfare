package top.andro.a_warfare.common;

import net.minecraft.resources.ResourceLocation;
import top.andro.a_warfare.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: andro
 */
public record FireMode(ResourceLocation id) {
    /**
     * A fire mode that shoots once per trigger press
     */
    public static final FireMode SEMI_AUTO = new FireMode(new ResourceLocation(Reference.MOD_ID, "semi_automatic"));

    /**
     * A fire mode that shoots as long as the trigger is held down
     */
    public static final FireMode AUTOMATIC = new FireMode(new ResourceLocation(Reference.MOD_ID, "automatic"));

    /**
     * A fire mode that shoots once per cooldown
     */
    public static final FireMode PULSE = new FireMode(new ResourceLocation(Reference.MOD_ID, "pulse"));
    /**
     * A fire mode that shoots a continuous beam
     */
    public static final FireMode BEAM = new FireMode(new ResourceLocation(Reference.MOD_ID, "beam"));

    /**
     * A fire mode that shoots a single beam per trigger press
     */
    public static final FireMode SEMI_BEAM = new FireMode(new ResourceLocation(Reference.MOD_ID, "semi_beam"));

    /**
     * A fire mode that shoots a burst of bullets
     */
    public static final FireMode BURST = new FireMode(new ResourceLocation(Reference.MOD_ID, "burst"));


    /**
     * The fire mode map.
     */
    private static final Map<ResourceLocation, FireMode> fireModeMap = new HashMap<>();

    static {
        /* Registers the standard fire modes when the class is loaded */
        registerType(SEMI_AUTO);
        registerType(AUTOMATIC);
        registerType(PULSE);
        registerType(BEAM);
        registerType(SEMI_BEAM);
        registerType(BURST);

    }

    /**
     * Registers a new fire mode. If the id already exists, the fire mode will simply be ignored.
     *
     * @param mode the get of the fire mode
     */
    public static void registerType(FireMode mode) {
        fireModeMap.putIfAbsent(mode.id(), mode);
    }

    /**
     * Gets the fire mode associated the the id. If the fire mode does not exist, it will default to
     * one handed.
     *
     * @param id the id of the fire mode
     * @return returns an get of the fire mode or SEMI_AUTO if it doesn't exist
     */
    public static FireMode getType(ResourceLocation id) {
        return fireModeMap.getOrDefault(id, SEMI_AUTO);
    }

    /**
     * Creates a new fire mode.
     *
     * @param id the id of the fire mode
     */
    public FireMode {
    }

    /**
     * Gets the id of the fire mode
     */
    @Override
    public ResourceLocation id() {
        return this.id;
    }

    public int ordinal() {
        return 0;
    }
}

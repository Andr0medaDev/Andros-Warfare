package top.andro.a_warfare.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;


public class RarityUtils {

    private static final Map<ResourceLocation, Rarity> ITEM_RARITY_MAP = new HashMap<>();

    static {
        // Add your custom items and their rarities here
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "floundergat"), Constants.OCEANIC);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "marlin"), Constants.OCEANIC);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "bomb_lance"), Constants.OCEANIC);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "ocean_blueprint"), Constants.OCEANIC);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "sequoia"), Constants.OCEANIC);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "super_shotgun"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "blasphemy"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "freyr"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "pyroclastic_flow"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "vulcanic_repeater"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "piglin_blueprint"), Constants.PIGLISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "mangalitsa"), Constants.PIGLISH);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "whispers"), Constants.DEEP_DARK);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "echoes_2"), Constants.DEEP_DARK);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "sculk_resonator"), Constants.DEEP_DARK);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "deep_dark_blueprint"), Constants.DEEP_DARK);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "end_blueprint"), Constants.ENDISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "lone_wonder"), Constants.ENDISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "raygun"), Constants.ENDISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "dark_matter"), Constants.ENDISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "shellurker"), Constants.ENDISH);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "carapice"), Constants.ENDISH);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "scorched_blueprint"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "scorched_ingot"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "earths_corpse"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "rat_king_and_queen"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "locust"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "newborn_cyst"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "astella"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "flayed_god"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "nervepinch"), Constants.SCORCHED);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "prima_materia"), Constants.SCORCHED);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "felix_memorial"), Constants.PIGLISH);

        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "ultra_knight_hawk"), Constants.BIZARRE);
        ITEM_RARITY_MAP.put(new ResourceLocation("a_warfare", "big_bore"), Constants.BIZARRE);

    }



    public static Rarity GetRarityFromResourceLocation(ResourceLocation location, Rarity oldRarity) {
        return ITEM_RARITY_MAP.getOrDefault(location, oldRarity);
    }

    public static Rarity GetRarityFromItem(Item item, Rarity old) {
        var items = ForgeRegistries.ITEMS;
        if (items.containsValue(item)) {
            return GetRarityFromResourceLocation(items.getKey(item), old);
        }
        return old;
    }
}

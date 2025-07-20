package top.andro.a_warfare.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.client.screen.*;


public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<RecipeSerializer<MechanicalPressRecipe>> MECHANICAL_PRESS_SERIALIZER =
            SERIALIZERS.register("mechanical_pressing", () -> MechanicalPressRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<PoweredMechanicalPressRecipe>> POWERED_MECHANICAL_PRESS_SERIALIZER =
            SERIALIZERS.register("powered_mechanical_pressing", () -> PoweredMechanicalPressRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<MaceratorRecipe>> MACERATOR_SERIALIZER =
            SERIALIZERS.register("macerating", () -> MaceratorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<PoweredMaceratorRecipe>> POWERED_MACERATOR_SERIALIZER =
            SERIALIZERS.register("powered_macerating", () -> PoweredMaceratorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<GunBenchRecipe>> GUN_BENCH_SERIALIZER =
            SERIALIZERS.register("gun_bench", () -> GunBenchRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<LightningBatteryRecipe>> LIGHTNING_BATTERY_SERIALIZER =
            SERIALIZERS.register("lightning_battery", () -> LightningBatteryRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}




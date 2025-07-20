package top.andro.a_warfare.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.world.GeothermalVentFeature;
import top.andro.a_warfare.world.SulfurVentFeature;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GEOTHERMAL_VENT_FEATURE = FEATURES.register("geothermal_vent",
            () -> new GeothermalVentFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SULFUR_VENT_FEATURE = FEATURES.register("sulfur_vent",
            () -> new SulfurVentFeature(NoneFeatureConfiguration.CODEC));


    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}
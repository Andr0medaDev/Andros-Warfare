package top.andro.a_warfare.world.gen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.init.ModBlocks;
import top.andro.a_warfare.init.ModFeatures;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANTHRALITE_ORE_KEY = registerKey("anthralite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_ORE_KEY = registerKey("sulfur_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SULFUR_ORE_KEY = registerKey("nether_sulfur_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEOTHERMAL_VENT_KEY = registerKey("geothermal_vent");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_VENT_KEY = registerKey("sulfur_vent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEHEMENT_COAL_ORE_KEY = registerKey("vehement_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RICH_PHOSPHORITE_ORE_KEY = registerKey("rich_phosphorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PHOSPHORITE_KEY = registerKey("phosphorite");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest phosphoriteReplaceables = new BlockMatchTest(ModBlocks.PHOSPHORITE.get());

        List<OreConfiguration.TargetBlockState> richPhosphoriteOres = List.of(
                OreConfiguration.target(phosphoriteReplaceables, ModBlocks.RICH_PHOSPHORITE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> phosphoriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.PHOSPHORITE.get().defaultBlockState())
        );

        register(context, PHOSPHORITE_KEY, Feature.ORE, new OreConfiguration(phosphoriteOres, 33));

        register(context, RICH_PHOSPHORITE_ORE_KEY, Feature.ORE, new OreConfiguration(richPhosphoriteOres, 8));

        List<OreConfiguration.TargetBlockState> overworldAnthraliteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ANTHRALITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ANTHRALITE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldSulfurOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SULFUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SULFUR_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> netherSulfurOres = List.of(
                OreConfiguration.target(netherrackReplaceables, ModBlocks.NETHER_SULFUR_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> netherVehementCoalOres = List.of(
                OreConfiguration.target(netherrackReplaceables, ModBlocks.VEHEMENT_COAL_ORE.get().defaultBlockState())
        );

        register(context, ANTHRALITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAnthraliteOres, 10));
        register(context, SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSulfurOres, 9));
        register(context, NETHER_SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(netherSulfurOres, 12));
        register(context, VEHEMENT_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(netherVehementCoalOres, 6));

        // Geothermal Vent Configuration
        register(context, GEOTHERMAL_VENT_KEY, ModFeatures.GEOTHERMAL_VENT_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);
        register(context, SULFUR_VENT_KEY, ModFeatures.SULFUR_VENT_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Reference.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

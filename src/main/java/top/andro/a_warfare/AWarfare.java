package top.andro.a_warfare;

import com.mrcrayfish.framework.api.FrameworkAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.andro.a_warfare.attributes.SCAttributes;
import top.andro.a_warfare.client.CustomGunManager;
import top.andro.a_warfare.client.handler.*;
import top.andro.a_warfare.client.screen.*;
import top.andro.a_warfare.common.BoundingBoxManager;
import top.andro.a_warfare.common.NetworkGunManager;
import top.andro.a_warfare.common.ProjectileManager;
import top.andro.a_warfare.compat.CreateModCondition;
import top.andro.a_warfare.compat.FarmersDelightModCondition;
import top.andro.a_warfare.compat.IEModCondition;
import top.andro.a_warfare.entity.config.ConfigLoader;
import top.andro.a_warfare.entity.projectile.*;
import top.andro.a_warfare.entity.throwable.GrenadeEntity;
import top.andro.a_warfare.event.*;
import top.andro.a_warfare.init.ModBlockEntities;
import top.andro.a_warfare.client.ClientHandler;
import top.andro.a_warfare.entity.config.CogMinionConfig;
import top.andro.a_warfare.init.*;
import top.andro.a_warfare.network.PacketHandler;
import top.andro.a_warfare.world.VillageStructures;
import java.io.IOException;
import java.io.InputStream;

import static top.andro.a_warfare.Reference.MOD_ID;

@Mod(MOD_ID)
public class AWarfare {
    public static final String MODID = "a_warfare";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static CogMinionConfig COG_MINION_CONFIG = new CogMinionConfig();
    public static boolean backpackedLoaded;
    public static boolean curiosLoaded;
    public static boolean controllableLoaded;
    public static boolean playerReviveLoaded;
    public static boolean createLoaded;
    public static boolean farmersDelightLoaded;
    public static boolean mekanismLoaded;
    public static boolean ieLoaded;
    public static boolean valkyrienSkiesLoaded;
    public static boolean marjLoaded;
    private static boolean useEnergyGuns = false;

    public AWarfare() {
        // Common setup
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        Config.GunScalingConfig.setup();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverSpec);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onConfigLoad);
        // Initialize the deferred register for items
        ModItems.REGISTER.register(bus);
        ModRecipes.register(modEventBus);

        SCAttributes.ATTRIBUTES.register(modEventBus);

        initializeModDependencies();
        ModItems.registerItems();
        // Register other mod features
        MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        ModCreativeModeTabs.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModBlocks.REGISTER.register(bus);
        ModContainers.REGISTER.register(bus);
        ModEffects.REGISTER.register(bus);
        ModMenuTypes.register(bus);
        ModEnchantments.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModParticleTypes.REGISTER.register(bus);
        ModRecipes.register(bus);
        ModSounds.REGISTER.register(bus);
        ModVillagers.register(bus);
        ModFeatures.register(bus);
        ModLootModifiers.LOOT_MODIFIERS.register(bus);
        ModPointOfInterestTypes.REGISTER.register(bus);
        ModRecipes.register(modEventBus);

        bus.addListener(this::onCommonSetup);

        // Ensure client-specific code is run only on the client side
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            ClientHandler.registerClientHandlers(bus);
            MinecraftForge.EVENT_BUS.register(HUDRenderHandler.class);
            MinecraftForge.EVENT_BUS.register(InspectHandler.get());
            CraftingHelper.register(CreateModCondition.Serializer.INSTANCE);
            CraftingHelper.register(FarmersDelightModCondition.Serializer.INSTANCE);
            CraftingHelper.register(IEModCondition.Serializer.INSTANCE);
            MinecraftForge.EVENT_BUS.register(BeamHandler.class);
        });

        // Register the mod itself to the event bus
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(ArmorBoostEventHandler.class);
       /// MinecraftForge.EVENT_BUS.register(ArmorRemoveEventHandler.class);
        MinecraftForge.EVENT_BUS.register(HeavyWeaponEventHandler.class);
        MinecraftForge.EVENT_BUS.register(OceanWeaponEventHandler.class);
        MinecraftForge.EVENT_BUS.register(PiglinWeaponEventHandler.class);
    }
    private void onConfigLoad(ModConfigEvent.Loading event) {
        if (event.getConfig().getType() == ModConfig.Type.SERVER) {
            RecoilHandler.get().updateConfig();
        }
    }

    private void onConfigReload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getType() == ModConfig.Type.SERVER) {
            RecoilHandler.get().updateConfig();
        }
    }
    private void initializeModDependencies() {
        // Check for optional mod dependencies
        valkyrienSkiesLoaded = ModList.get().isLoaded("valkyrienskies");
        controllableLoaded = ModList.get().isLoaded("controllable");
        backpackedLoaded = ModList.get().isLoaded("backpacked");
        curiosLoaded = ModList.get().isLoaded("curios");
        playerReviveLoaded = ModList.get().isLoaded("playerrevive");
        createLoaded = ModList.get().isLoaded("create");
        farmersDelightLoaded = ModList.get().isLoaded("farmersdelight");
        ieLoaded = ModList.get().isLoaded("immersiveengineering");
        mekanismLoaded = ModList.get().isLoaded("mekanism");
        marjLoaded = ModList.get().isLoaded("majruszsdifficulty");
    }


    public static boolean shouldUseEnergyGuns() {
        return !createLoaded || useEnergyGuns;
    }
    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PacketHandler.init();
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.AIMING);
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.RELOADING);
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.SHOOTING);
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.BURSTCOUNT);
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.ONBURSTCOOLDOWN);
            FrameworkAPI.registerSyncedDataKey(ModSyncedDataKeys.MELEE);
            MinecraftForge.EVENT_BUS.register(TemporaryLightManager.class);
            // Register login data
            FrameworkAPI.registerLoginData(new ResourceLocation(Reference.MOD_ID, "network_gun_manager"), NetworkGunManager.LoginData::new);
            FrameworkAPI.registerLoginData(new ResourceLocation(Reference.MOD_ID, "custom_gun_manager"), CustomGunManager.LoginData::new);

            ProjectileManager.getInstance().registerFactory(ModItems.POWDER_AND_BALL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.GRAPESHOT.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.COMPACT_COPPER_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.HOG_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new HogRoundProjectileEntity(ModEntities.HOG_ROUND_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.STANDARD_COPPER_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.COMPACT_ADVANCED_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.RAMROD_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new RamrodProjectileEntity(ModEntities.RAMROD_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.ADVANCED_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new AdvancedRoundProjectileEntity(ModEntities.ADVANCED_ROUND_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.KRAHG_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new KrahgRoundProjectileEntity(ModEntities.KRAHG_ROUND_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.BEOWULF_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new BeowulfProjectileEntity(ModEntities.BEOWULF_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.GIBBS_ROUND.get(), (worldIn, entity, weapon, item, modifiedGun) -> new GibbsRoundProjectileEntity(ModEntities.GIBBS_ROUND_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.SHOTGUN_SHELL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.BEARPACK_SHELL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new BearPackShellProjectileEntity(ModEntities.BEARPACK_SHELL_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.BLAZE_FUEL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new FireRoundEntity(ModEntities.FIRE_ROUND_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.SCULK_CELL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new SculkCellEntity(ModEntities.SCULK_CELL.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.SHOCK_CELL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new LightningProjectileEntity(ModEntities.PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.SHULKSHOT.get(), (worldIn, entity, weapon, item, modifiedGun) -> new ShulkshotProjectileEntity(ModEntities.SHULKSHOT.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.ENERGY_CELL.get(), (worldIn, entity, weapon, item, modifiedGun) -> new PlasmaProjectileEntity(ModEntities.PLASMA_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.OSBORNE_SLUG.get(), (worldIn, entity, weapon, item, modifiedGun) -> new OsborneSlugProjectileEntity(ModEntities.OSBORNE_SLUG_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(Items.BLAZE_ROD, (worldIn, entity, weapon, item, modifiedGun) -> new BlazeRodProjectileEntity(ModEntities.BLAZE_ROD_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.SYRINGE.get(), (worldIn, entity, weapon, item, modifiedGun) -> new SyringeProjectileEntity(ModEntities.SYRINGE_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.STANDARD_BULLET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new BasicBulletProjectileEntity(ModEntities.BASIC_BULLET_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.ADVANCED_BULLET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new HardenedBulletProjectileEntity(ModEntities.HARDENED_BULLET_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.BUCKSHOT.get(), (worldIn, entity, weapon, item, modifiedGun) -> new BuckshotProjectileEntity(ModEntities.BUCKSHOT_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.NITRO_BUCKSHOT.get(), (worldIn, entity, weapon, item, modifiedGun) -> new BuckshotProjectileEntity(ModEntities.BUCKSHOT_PROJECTILE.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.ROCKET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new RocketEntity(ModEntities.ROCKET.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.MICROJET.get(), (worldIn, entity, weapon, item, modifiedGun) -> new MicroJetEntity(ModEntities.MICROJET.get(), worldIn, entity, weapon, item, modifiedGun));
            ProjectileManager.getInstance().registerFactory(ModItems.GRENADE.get(), (worldIn, entity, weapon, item, modifiedGun) -> new GrenadeEntity(ModEntities.GRENADE.get(), worldIn, entity, weapon, item, modifiedGun));
            useEnergyGuns = Config.COMMON.gameplay.forceEnergyGuns.get();

          if (Config.COMMON.gameplay.improvedHitboxes.get()) {
                MinecraftForge.EVENT_BUS.register(new BoundingBoxManager());
            }
            try {
                InputStream inputStream = AWarfare.class.getClassLoader().getResourceAsStream("data/a_warfare/entity/cog_minion_item.json");
                if (inputStream != null) {
                    COG_MINION_CONFIG = ConfigLoader.loadCogMinionConfig(inputStream);
                    inputStream.close();
                } else {
                    LOGGER.error("Could not find Cog Minion config");
                }
            } catch (IOException e) {
                LOGGER.error("Failed to load Cog Minion config", e);
            }
        });
    }
    public static boolean isDebugging() {
        return false;
    }
}


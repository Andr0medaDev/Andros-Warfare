package top.andro.a_warfare.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.entity.client.*;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEventsBus {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.COG_MINION_LAYER, CogMinionModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.COG_KNIGHT_LAYER, CogKnightModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKY_CARRIER_LAYER, SkyCarrierModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SUPPLY_SCAMP_LAYER, SupplyScampModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.REDCOAT_LAYER, RedcoatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BLUNDERER_LAYER, BlundererModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DISSIDENT_LAYER, DissidentModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HIVE_LAYER, HiveModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SWARM_LAYER, SwarmModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HORNLIN_LAYER, HornlinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ZOMBIFIED_HORNLIN_LAYER, ZombifiedHornlinModel::createBodyLayer);
    }
}


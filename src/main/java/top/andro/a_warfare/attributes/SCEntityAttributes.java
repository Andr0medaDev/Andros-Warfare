package top.andro.a_warfare.attributes;

import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EntityType;
import top.andro.a_warfare.AWarfare;

@Mod.EventBusSubscriber(modid = AWarfare.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCEntityAttributes {
    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, SCAttributes.PROJECTILE_SPEED.get());
        event.add(EntityType.PLAYER, SCAttributes.RELOAD_SPEED.get());
        event.add(EntityType.PLAYER, SCAttributes.ADDITIONAL_BULLET_DAMAGE.get());
        event.add(EntityType.PLAYER, SCAttributes.BULLET_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, SCAttributes.SPREAD_MULTIPLIER.get());
        //Disabled
        //event.add(EntityType.PLAYER, SCAttributes.BULLET_RESISTANCE.get());
        //event.add(EntityType.PLAYER, SCAttributes.FIRE_RATE_MULTIPLIER.get());
    }
}
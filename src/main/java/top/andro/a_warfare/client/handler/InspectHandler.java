package top.andro.a_warfare.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationController;
import top.andro.a_warfare.client.KeyBinds;
import top.andro.a_warfare.item.animated.AnimatedGunItem;

public class InspectHandler {
    private static InspectHandler instance;

    public static InspectHandler get() {
        if (instance == null) {
            instance = new InspectHandler();
        }
        return instance;
    }

    private InspectHandler() {
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.getItem() instanceof AnimatedGunItem gunItem) {
                if (KeyBinds.KEY_INSPECT.isDown() && event.getAction() == 1) {
                    long id = GeoItem.getId(heldItem);
                    AnimationController<GeoAnimatable> animationController = gunItem.getAnimatableInstanceCache()
                            .getManagerForId(id)
                            .getAnimationControllers()
                            .get("controller");
                    if (animationController != null) {
                        boolean canInspect = true;
                        if (animationController.getCurrentAnimation() != null) {
                            String currentAnim = animationController.getCurrentAnimation().animation().name();
                            canInspect = !currentAnim.contains("draw") &&
                                    !currentAnim.contains("reload") &&
                                    !AimingHandler.get().isAiming();
                        }

                        if (canInspect) {
                            animationController.setAnimationSpeed(1.0);
                            animationController.forceAnimationReset();
                            boolean isCarbine = gunItem.isInCarbineMode(heldItem);
                            String animToPlay = isCarbine ? "carbine_inspect" : "inspect";
                            animationController.tryTriggerAnimation(animToPlay);
                        }
                    }
                }
            }
        }
    }
}
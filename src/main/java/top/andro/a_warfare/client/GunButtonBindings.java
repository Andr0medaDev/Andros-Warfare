package top.andro.a_warfare.client;

import com.mrcrayfish.controllable.client.binding.BindingRegistry;
import com.mrcrayfish.controllable.client.binding.ButtonBinding;
import com.mrcrayfish.controllable.client.input.Buttons;

/**
 * Author: MrCrayfish
 */
public class GunButtonBindings
{
    public static final ButtonBinding SHOOT = new ButtonBinding(Buttons.RIGHT_TRIGGER, "a_warfare.button.shoot", "button.categories.a_warfare", GunConflictContext.IN_GAME_HOLDING_WEAPON);
    public static final ButtonBinding AIM = new ButtonBinding(Buttons.LEFT_TRIGGER, "a_warfare.button.aim", "button.categories.a_warfare", GunConflictContext.IN_GAME_HOLDING_WEAPON);
    public static final ButtonBinding RELOAD = new ButtonBinding(Buttons.X, "a_warfare.button.reload", "button.categories.a_warfare", GunConflictContext.IN_GAME_HOLDING_WEAPON);
    public static final ButtonBinding OPEN_ATTACHMENTS = new ButtonBinding(Buttons.B, "a_warfare.button.attachments", "button.categories.a_warfare", GunConflictContext.IN_GAME_HOLDING_WEAPON);
    public static final ButtonBinding STEADY_AIM = new ButtonBinding(Buttons.RIGHT_THUMB_STICK, "a_warfare.button.steadyAim", "button.categories.a_warfare", GunConflictContext.IN_GAME_HOLDING_WEAPON);

    public static void register()
    {
        BindingRegistry.getInstance().register(SHOOT);
        BindingRegistry.getInstance().register(AIM);
        BindingRegistry.getInstance().register(RELOAD);
        BindingRegistry.getInstance().register(OPEN_ATTACHMENTS);
        BindingRegistry.getInstance().register(STEADY_AIM);

    }
}

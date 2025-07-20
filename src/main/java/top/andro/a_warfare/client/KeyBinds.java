package top.andro.a_warfare.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import top.andro.a_warfare.Config;

/**
 * Author: MrCrayfish
 */
    public class KeyBinds
    {
        public static final KeyMapping KEY_RELOAD = new KeyMapping("key.a_warfare.reload", GLFW.GLFW_KEY_R, "key.categories.a_warfare");
        public static final KeyMapping KEY_UNLOAD = new KeyMapping("key.a_warfare.unload", GLFW.GLFW_KEY_U, "key.categories.a_warfare");
        public static final KeyMapping KEY_ATTACHMENTS = new KeyMapping("key.a_warfare.attachments", GLFW.GLFW_KEY_Z, "key.categories.a_warfare");
        public static final KeyMapping KEY_MELEE = new KeyMapping("key.a_warfare.melee", GLFW.GLFW_KEY_V, "key.categories.a_warfare");
        public static final KeyMapping KEY_INSPECT = new KeyMapping("key.a_warfare.inspect", GLFW.GLFW_KEY_X, "key.categories.a_warfare");
        public static void registerKeyMappings(RegisterKeyMappingsEvent event)
        {
            event.register(KEY_RELOAD);
            event.register(KEY_UNLOAD);
            event.register(KEY_ATTACHMENTS);
            event.register(KEY_MELEE);
            event.register(KEY_INSPECT);
        }

        public static KeyMapping getAimMapping()
        {
            Minecraft mc = Minecraft.getInstance();
            return Config.CLIENT.controls.flipControls.get() ? mc.options.keyAttack : mc.options.keyUse;
        }

        public static KeyMapping getShootMapping()
        {
            Minecraft mc = Minecraft.getInstance();
            return Config.CLIENT.controls.flipControls.get() ? mc.options.keyUse : mc.options.keyAttack;
        }
    }

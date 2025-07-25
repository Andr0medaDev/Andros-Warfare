package top.andro.a_warfare.common;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import top.andro.a_warfare.Reference;
import top.andro.a_warfare.client.render.IHeldAnimation;
import top.andro.a_warfare.client.render.pose.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
public record GripType(ResourceLocation id, IHeldAnimation heldAnimation) {
    /**
     * A grip type designed for weapons that are held with only one hand, like a pistol
     */
    public static final GripType ONE_HANDED = new GripType(new ResourceLocation(Reference.MOD_ID, "one_handed"), new OneHandedPose());

    public static final GripType ONE_HANDED_2 = new GripType(new ResourceLocation(Reference.MOD_ID, "one_handed_2"), new OneHanded2Pose());
    /**
     * A grip type designed for weapons that are held with two hands, like an assault rifle
     */
    public static final GripType TWO_HANDED = new GripType(new ResourceLocation(Reference.MOD_ID, "two_handed"), new TwoHandedPose());
    public static final GripType TWO_HANDED_SHOTGUN = new GripType(new ResourceLocation(Reference.MOD_ID, "two_handed_shotgun"), new TwoHandedShotgunPose());
    public static final GripType TWO_HANDED_SMG = new GripType(new ResourceLocation(Reference.MOD_ID, "two_handed_smg"), new TwoHandedSmgPose());
   public static final GripType DUAL_WIELD = new GripType(new ResourceLocation(Reference.MOD_ID, "dual_wield"), new DualWieldPose());
    /**
     * A custom grip type designed for the mini gun simply due it's nature of being a completely
     * unique way to hold the weapon
     */
    public static final GripType MINI_GUN = new GripType(new ResourceLocation(Reference.MOD_ID, "mini_gun"), new MiniGunPose());

    /**
     * A custom grip type designed for the mini gun simply due it's nature of being a completely
     * unique way to hold the weapon
     */
    public static final GripType MINI_GUN_2 = new GripType(new ResourceLocation(Reference.MOD_ID, "mini_gun_2"), new MiniGun2Pose());


    public static final GripType MINI_GUN_3 = new GripType(new ResourceLocation(Reference.MOD_ID, "mini_gun_3"), new MiniGun3Pose());
    public static final GripType MINI_GUN_4 = new GripType(new ResourceLocation(Reference.MOD_ID, "mini_gun_4"), new MiniGun4Pose());
    public static final GripType MINI_GUN_5 = new GripType(new ResourceLocation(Reference.MOD_ID, "mini_gun_5"), new MiniGun5Pose());

    /**
     * A custom grip type designed for the bazooka.
     */
    public static final GripType BAZOOKA = new GripType(new ResourceLocation(Reference.MOD_ID, "bazooka"), new BazookaPose());

    /**
     * A common method to set up a transformation of the weapon onto the players' back.
     *
     * @param player    the player the weapon is being rendered on
     * @param poseStack the matrixstack get
     * @return if the weapon can render
     */
    public static boolean applyBackTransforms(Player player, PoseStack poseStack) {
        if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
            return false;
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(180F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));

        if (player.isCrouching()) {
            poseStack.translate(0 * 0.0625, -7 * 0.0625, -4 * 0.0625);
            poseStack.mulPose(Axis.XP.rotationDegrees(30F));
        } else {
            poseStack.translate(0 * 0.0625, -5 * 0.0625, -2 * 0.0625);
        }

        if (!player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            poseStack.translate(0, 0, -1 * 0.0625);
        }

        poseStack.mulPose(Axis.ZP.rotationDegrees(-45F));
        poseStack.scale(0.5F, 0.5F, 0.5F);

        return true;
    }

    /**
     * The grip type map.
     */
    private static final Map<ResourceLocation, GripType> gripTypeMap = new HashMap<>();

    static {
        /* Registers the standard grip types when the class is loaded */
        registerType(ONE_HANDED);
        registerType(ONE_HANDED_2);
        registerType(TWO_HANDED);
        registerType(TWO_HANDED_SMG);
        registerType(TWO_HANDED_SHOTGUN);
        registerType(DUAL_WIELD);
        registerType(MINI_GUN);
        registerType(MINI_GUN_2);
        registerType(MINI_GUN_3);
        registerType(MINI_GUN_4);
        registerType(MINI_GUN_5);
        registerType(BAZOOKA);
    }

    /**
     * Registers a new grip type. If the id already exists, the grip type will simply be ignored.
     *
     * @param type the get of the grip type
     */
    public static void registerType(GripType type) {
        gripTypeMap.putIfAbsent(type.id(), type);
    }

    /**
     * Gets the grip type associated the the id. If the grip type does not exist, it will default to
     * one handed.
     *
     * @param id the id of the grip type
     * @return returns an get of the grip type or ONE_HANDED if it doesn't exist
     */
    public static GripType getType(ResourceLocation id) {
        return gripTypeMap.getOrDefault(id, ONE_HANDED);
    }

    /**
     * Creates a new grip type.
     *
     * @param id            the id of the grip type
     * @param heldAnimation the animation functions to apply to the held weapon
     */
    public GripType {
    }

    /**
     * Gets the id of the grip type
     */
    @Override
    public ResourceLocation id() {
        return this.id;
    }

    /**
     * Gets the held animation get. Used for rendering
     */
    @Override
    public IHeldAnimation heldAnimation() {
        return this.heldAnimation;
    }
}

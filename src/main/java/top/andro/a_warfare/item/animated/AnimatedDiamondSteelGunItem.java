package top.andro.a_warfare.item.animated;

import net.minecraft.sounds.SoundEvent;

public class AnimatedDiamondSteelGunItem extends AnimatedSilencedGunItem {
    public AnimatedDiamondSteelGunItem(Properties properties, String path, SoundEvent reloadSoundMagOut, SoundEvent reloadSoundMagIn, SoundEvent reloadSoundEnd, SoundEvent boltPullSound, SoundEvent boltReleaseSound) {
        super(properties, path, reloadSoundMagOut, reloadSoundMagIn, reloadSoundEnd, boltPullSound, boltReleaseSound);
    }
    @Override
    public int getEnchantmentValue() {
        return 18;
    }
}

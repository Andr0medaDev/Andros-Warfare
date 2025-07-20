package top.andro.a_warfare.item.animated;

import net.minecraft.sounds.SoundEvent;

public class AnimatedDualWieldGunItem extends AnimatedGunItem{
    public AnimatedDualWieldGunItem(Properties properties, String path, SoundEvent reloadSoundMagOut, SoundEvent reloadSoundMagIn, SoundEvent reloadSoundEnd, SoundEvent boltPullSound, SoundEvent boltReleaseSound) {
        super(properties, path, reloadSoundMagOut, reloadSoundMagIn, reloadSoundEnd, boltPullSound, boltReleaseSound);
    }
    @Override
    public boolean isFireResistant() {
        return true;
    }
}

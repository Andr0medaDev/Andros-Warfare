package top.andro.a_warfare.item.animated;

import net.minecraft.sounds.SoundEvent;

public class NonUnderwaterAnimatedGunItem extends AnimatedGunItem {
    public NonUnderwaterAnimatedGunItem(Properties properties, String path, SoundEvent reloadSoundMagOut, SoundEvent reloadSoundMagIn, SoundEvent reloadSoundEnd, SoundEvent boltPullSound, SoundEvent boltReleaseSound) {
        super(properties, path, reloadSoundMagOut, reloadSoundMagIn, reloadSoundEnd, boltPullSound, boltReleaseSound);
    }
}

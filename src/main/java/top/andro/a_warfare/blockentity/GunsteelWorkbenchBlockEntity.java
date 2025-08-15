package top.andro.a_warfare.blockentity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import top.andro.a_warfare.blockentity.inventory.IStorageBlock;
import top.andro.a_warfare.common.container.GunsteelWorkbenchContainer;
import top.andro.a_warfare.init.ModBlockEntities;

import javax.annotation.Nullable;


public class GunsteelWorkbenchBlockEntity extends SyncedBlockEntity implements IStorageBlock {
    private final NonNullList<ItemStack> inventory  = NonNullList.withSize(1, ItemStack.EMPTY);

    public GunsteelWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GUNSTEEL_WORKBENCH.get(), pos, state);
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return this.inventory;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, this.inventory);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return index != 0 || (stack.getItem() instanceof DyeItem && this.inventory.get(index).getCount() < 1);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.level.getBlockEntity(this.worldPosition) == this && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.a_warfare.gunsteel_workbench");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
        return new GunsteelWorkbenchContainer(windowId, playerInventory, this);
    }
}
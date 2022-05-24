/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.common.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

import net.dries007.tfc.common.blockentities.BlastFurnaceBlockEntity;

public class BlastFurnaceContainer extends BlockEntityContainer<BlastFurnaceBlockEntity>
{
    public static BlastFurnaceContainer create(BlastFurnaceBlockEntity blastFurnace, Inventory playerInventory, int windowId)
    {
        return new BlastFurnaceContainer(windowId, blastFurnace).init(playerInventory);
    }

    private BlastFurnaceContainer(int windowId, BlastFurnaceBlockEntity blockEntity)
    {
        super(TFCContainerTypes.BLAST_FURNACE.get(), windowId, blockEntity);
    }

    @Override
    protected boolean moveStack(ItemStack stack, int slotIndex)
    {
        // todo: impl
        return switch (typeOf(slotIndex))
            {
                case CONTAINER -> true;
                case HOTBAR -> !moveItemStackTo(stack, containerSlots, containerSlots + 27, false);
                case MAIN_INVENTORY -> !moveItemStackTo(stack, containerSlots + 27, containerSlots + 36, false);
            };
    }

    @Override
    protected void addContainerSlots()
    {
        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            addSlot(new CallbackSlot(blockEntity, handler, 0, 0, 0)); // todo: slot x, y
        });
    }
}

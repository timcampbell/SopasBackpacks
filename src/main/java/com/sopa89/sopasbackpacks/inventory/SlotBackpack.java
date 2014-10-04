package com.sopa89.sopasbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sopa89.sopasbackpacks.item.ItemBackpack;

public class SlotBackpack extends Slot
{
	private final EntityPlayer player;
	private ContainerBackpack backpackContainer;
	
	public SlotBackpack(ContainerBackpack containerBackpack, IInventory inventory, EntityPlayer entityPlayer, int slotIndex, int x, int y)
	{
		super(inventory, slotIndex, x, y);
		this.player=entityPlayer;
		this.backpackContainer=containerBackpack;
	}
	
	@Override
	public void onSlotChange(ItemStack stack1, ItemStack stack2)
	{
		super.onSlotChange(stack1, stack2);
		backpackContainer.saveInventory(player);
	}
	
	//validate the item is valid for the slot
	//always true for all but other backpacks
	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return !(itemStack.getItem() instanceof ItemBackpack);
	}
	
}

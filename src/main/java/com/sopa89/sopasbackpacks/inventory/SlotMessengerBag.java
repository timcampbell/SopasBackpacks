package com.sopa89.sopasbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sopa89.sopasbackpacks.item.ItemBackpack;
import com.sopa89.sopasbackpacks.item.ItemMessengerBag;

public class SlotMessengerBag extends Slot
{
	private final EntityPlayer entityPlayer;
	private ContainerMessengerBag messengerContainer;
	
	public SlotMessengerBag(ContainerMessengerBag messengerBagContainer, IInventory inventory , EntityPlayer player, int slotIndex, int x, int y)
	{
		super(inventory, slotIndex, x, y);
		
		this.messengerContainer=messengerBagContainer;
		this.entityPlayer=player
	}
	
	@Override
	public void onSlotChange(ItemStack stack1, ItemStack stack2)
	{
		super.onSlotChange(stack1, stack2);
		messengerContainer.saveInventory(entityPlayer);
	}
	
	//Validates Item for Slot
	//true for all items except backpacks and messenger bags
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof ItemBackpack || stack.getItem() instanceof ItemMessengerBag
	}
}

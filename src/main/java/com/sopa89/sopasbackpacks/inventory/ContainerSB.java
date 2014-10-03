package com.sopa89.sopasbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sopa89.sopasbackpacks.utility.ItemHelper;

public abstract class ContainerSB extends Container
{
	protected final int PLAYER_INVENTORY_ROWS=3;
	protected final int PLAYER_INVENTORY_COLUMNS=9;
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean mergeItemStack(ItemStack stack, int minSlot, int maxSlot, boolean ascending)
	{
		boolean slotFound=false;
		
		int currentSlotIndex=ascending? maxSlot-1: minSlot;
		
		Slot slot;
		ItemStack stackInSlot;
		
		if(stack.isStackable())
		{
			while(stack.stackSize>0 && (!ascending && currentSlotIndex<maxSlot || ascending && currentSlotIndex>=minSlot))
			{
				slot=(Slot)this.inventorySlots.get(currentSlotIndex);
				stackInSlot=slot.getStack();
				
				if(slot.isItemValid(stack) && ItemHelper.equalsIgnoreStackSize(stack, stackInSlot))
				{
					int combinedStackSize=stackInSlot.stackSize+stack.stackSize;
					int slotStackSizeLimit=Math.min(stackInSlot.getMaxStackSize(), stack.getMaxStackSize());
					
					if(combinedStackSize<slotStackSizeLimit)
					{
						stack.stackSize=0;
						stackInSlot.stackSize=combinedStackSize;
						slot.onSlotChanged();
						slotFound=true;
					}
					else if(stackInSlot.stackSize<slotStackSizeLimit)
					{
						stack.stackSize-=slotStackSizeLimit-stackInSlot.stackSize;
						stackInSlot.stackSize=slotStackSizeLimit;
						slot.onSlotChanged();
						slotFound=true;
					}
				}
				currentSlotIndex+=ascending? -1: 1;
			}
		}
		
		if(stack.stackSize>0)
		{
			currentSlotIndex=ascending? maxSlot-1: minSlot;
			
			while(!ascending && currentSlotIndex<maxSlot || ascending && currentSlotIndex>minSlot)
			{
				slot=(Slot)this.inventorySlots.get(currentSlotIndex);
				stackInSlot=slot.getStack();
				
				if(slot.isItemValid(stack) && stackInSlot==null)
				{
					slot.putStack(ItemHelper.cloneItemStack(stack, Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit())));
					slot.onSlotChanged();
					
					if(slot.getStack() != null)
					{
						stack.stackSize-=slot.getStack().stackSize;
						slotFound=true;
					}
					break;
				}
				currentSlotIndex+=ascending? -1: 1;
			}
		}
		return slotFound;
	}
}

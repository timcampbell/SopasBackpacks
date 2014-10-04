package com.sopa89.sopasbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sopa89.sopasbackpacks.item.ItemBackpack;

public class ContainerBackpack extends ContainerSB
{
		public static final int BACKPACK_ROWS=3;
		public static final int BACKPACK_COLUMNS=9;
		
		private EntityPlayer player;
		private InventoryBackpack backpackInv;
		
		private int backpackInvRows;
		private int backpackInvColumns;
		
		public ContainerBackpack(EntityPlayer entityPlayer, InventoryBackpack backpackInventory)
		{
			this.player=entityPlayer;
			this.backpackInv=backpackInventory;
			
			backpackInvRows=BACKPACK_ROWS;
			backpackInvColumns=BACKPACK_COLUMNS;
			
			
			//add backpack Inventory slots to container
			for(int backpackRowIndex=0; backpackRowIndex<backpackInvRows; backpackRowIndex++)
			{
				for(int backpackColumnIndex=0; backpackColumnIndex<backpackInvColumns; backpackColumnIndex++)
				{
					this.addSlotToContainer(new SlotBackpack(this, backpackInv, player, backpackColumnIndex+backpackRowIndex*backpackInvColumns, 8+backpackColumnIndex*18, 18+backpackRowIndex*18));
				}
			}
			
			//add player inventory slots to container
			for(int inventoryRowIndex=0; inventoryRowIndex<PLAYER_INVENTORY_ROWS; inventoryRowIndex++)
			{
				for(int inventoryColumnIndex=0; inventoryColumnIndex<PLAYER_INVENTORY_COLUMNS; inventoryColumnIndex++)
				{
					this.addSlotToContainer(new Slot(player.inventory, inventoryColumnIndex+inventoryRowIndex*9+9, 8+(inventoryColumnIndex*18), 86+(inventoryRowIndex*18)));
				}
			}
			
			//add player hotbar to container
			for(int hotbarSlotIndex=0; hotbarSlotIndex<PLAYER_INVENTORY_COLUMNS; hotbarSlotIndex++)
			{
				this.addSlotToContainer(new Slot(player.inventory, hotbarSlotIndex, 8+hotbarSlotIndex*18, 145));
			}
		}
		
		@Override
		public void onContainerClosed(EntityPlayer entityPlayer)
		{
			super.onContainerClosed(entityPlayer);
			
			saveInventory(entityPlayer);
		}
		
		@Override
		public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
		{
//			ItemStack newStack=null;
//			Slot slot=(Slot) inventorySlots.get(slotIndex);
//			
//			if(slot!=null)
//			{
//				ItemStack stack=slot.getStack();
//				newStack=stack.copy();
//				
//				//Attempt to shift click from backpack to player
//				if(slotIndex<backpackInvRows*backpackInvColumns)
//				{
//					if(!this.mergeItemStack(stack, backpackInvRows*backpackInvColumns, inventorySlots.size(), false))
//					{
//						return null;
//					}
//				}
//				
//				//Special Case if a backpack is being shift clicked
//				else if(stack.getItem() instanceof ItemBackpack)
//				{
//					//Attempt to move from inventory to hotbar
//					if(slotIndex<(backpackInvRows*backpackInvColumns)+(PLAYER_INVENTORY_ROWS*PLAYER_INVENTORY_COLUMNS))
//					{
//						if(!this.mergeItemStack(stack, (backpackInvRows*backpackInvColumns)+(PLAYER_INVENTORY_ROWS*PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false))
//						{
//							return null;
//						}
//					}
//					//Attempt to move from hotbar to player inventory
//					else if(!this.mergeItemStack(stack, backpackInvRows*backpackInvColumns, (backpackInvRows*backpackInvColumns)+(PLAYER_INVENTORY_ROWS*PLAYER_INVENTORY_COLUMNS), false))
//					{
//						return null;
//					}
//				}
//				//Attempt to shift click non-backpack items into backpack inventory
//				else if(!this.mergeItemStack(stack, 0, backpackInvRows*backpackInvColumns, false))
//				{
//					return null;
//				}
//				
//				if(stack.stackSize==0)
//				{
//					slot.putStack(null);
//				}
//				else
//				{
//					slot.onSlotChanged();
//				}
//			}
//			return newStack;
			
			ItemStack newItemStack = null;
	        Slot slot = (Slot) inventorySlots.get(slotIndex);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemStack = slot.getStack();
	            newItemStack = itemStack.copy();

	            // Attempt to shift click something from the bag inventory into the player inventory
	            if (slotIndex < backpackInvRows * backpackInvColumns)
	            {
	                if (!this.mergeItemStack(itemStack, backpackInvRows * backpackInvColumns, inventorySlots.size(), false))
	                {
	                    return null;
	                }
	            }
	            // Special case if we are dealing with an Alchemical Bag being shift clicked
	            else if (itemStack.getItem() instanceof ItemBackpack)
	            {
	                // Attempt to shift click a bag from the player inventory into the hot bar inventory
	                if (slotIndex < (backpackInvRows * backpackInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS))
	                {
	                    if (!this.mergeItemStack(itemStack, (backpackInvRows * backpackInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false))
	                    {
	                        return null;
	                    }
	                }
	                // Attempt to shift click a bag from the hot bar inventory into the player inventory
	                else if (!this.mergeItemStack(itemStack, backpackInvRows * backpackInvColumns, (backpackInvRows * backpackInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), false))
	                {
	                    return null;
	                }
	            }
	            // Attempt to shift click a non-Alchemical Bag into the bag inventory
	            else if (!this.mergeItemStack(itemStack, 0, backpackInvRows * backpackInvColumns, false))
	            {
	                return null;
	            }


	            if (itemStack.stackSize == 0)
	            {
	                slot.putStack(null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }
	        }

	        return newItemStack;
		}

		public void saveInventory(EntityPlayer entityPlayer)
		{
			backpackInv.onGuiSaved(entityPlayer);
		}
}

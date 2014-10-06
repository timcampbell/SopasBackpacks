package com.sopa89.sopasbackpacks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sopa89.sopasbackpacks.item.ItemBackpack;
import com.sopa89.sopasbackpacks.item.ItemMessengerBag;

public class ContainerMessengerBag extends ContainerSB
{
	public static final int MESSENGER_BAG_ROWS=6;
	public static final int MESSENGER_BAG_COLUMNS=9;
	
	private EntityPlayer player;
	private InventoryMessengerBag messengerBagInv;
	
	private int messengerBagInvRows;
	private int messengerBagInvColumns;
	
	public ContainerMessengerBag(EntityPlayer entityPlayer, InventoryMessengerBag inventoryMessengerBag)
	{
		this.player=entityPlayer;
		this.messengerBagInv=inventoryMessengerBag;
		
		messengerBagInvRows=MESSENGER_BAG_ROWS;
		messengerBagInvColumns=MESSENGER_BAG_COLUMNS;
		
		//add MessengerBag inventory Slots to container
		for(int messengerBagRowIndex=0; messengerBagRowIndex<messengerBagInvRows; messengerBagRowIndex++)
		{
			for(int messengerBagColumnIndex=0; messengerBagColumnIndex<messengerBagInvColumns; messengerBagColumnIndex++)
			{
				this.addSlotToContainer(new SlotBackpack(this, messengerBagInv, player, messengerBagColumnIndex+messengerBagRowIndex*messengerBagInvColumns, 8+messengerBagColumnIndex*18, 18+messengerBagRowIndex*18));
			}
		}
		
		//add player inventory slots to container
		for(int inventoryRowIndex=0; inventoryRowIndex<PLAYER_INVENTORY_ROWS; inventoryRowIndex++)
		{
			for(int inventoryColumnIndex=0; inventoryColumnIndex<PLAYER_INVENTORY_COLUMNS; inventoryColumnIndex++)
			{
				this.addSlotToContainer(new Slot(player.inventory, inventoryColumnIndex+inventoryRowIndex*9+9, 8+(inventoryColumnIndex*18), 140+(inventoryRowIndex*18)));
			}
		}
		
		//add player hotbar to container
		for(int hotbarSlotIndex=0; hotbarSlotIndex<PLAYER_INVENTORY_COLUMNS; hotbarSlotIndex++)
		{
			this.addSlotToContainer(new Slot(player.inventory, hotbarSlotIndex, 8+hotbarSlotIndex*18, 198));
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
		ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            // Attempt to shift click something from the bag inventory into the player inventory
            if (slotIndex < messengerBagInvRows * messengerBagInvColumns)
            {
                if (!this.mergeItemStack(itemStack, messengerBagInvRows * messengerBagInvColumns, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            // Special case if we are dealing with an backpack being shift clicked
            else if (itemStack.getItem() instanceof ItemBackpack || itemStack.getItem() instanceof ItemMessengerBag)
            {
                // Attempt to shift click a backpack from the player inventory into the hot bar inventory
                if (slotIndex < (messengerBagInvRows * messengerBagInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS))
                {
                    if (!this.mergeItemStack(itemStack, (messengerBagInvRows * messengerBagInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false))
                    {
                        return null;
                    }
                }
                // Attempt to shift click a backpack from the hot bar inventory into the player inventory
                else if (!this.mergeItemStack(itemStack, messengerBagInvRows * messengerBagInvColumns, (messengerBagInvRows * messengerBagInvColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), false))
                {
                    return null;
                }
            }
            // Attempt to shift click a non-backpack into the bag inventory
            else if (!this.mergeItemStack(itemStack, 0, messengerBagInvRows * messengerBagInvColumns, false))
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
		messengerBagInv.onGuiSaved(entityPlayer);
	}
}

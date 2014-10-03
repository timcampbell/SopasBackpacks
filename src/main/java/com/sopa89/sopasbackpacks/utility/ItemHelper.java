package com.sopa89.sopasbackpacks.utility;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHelper 
{
	
	
	public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2)
	{
		if (itemStack1 != null && itemStack2 != null)
		{
			// Sort on itemID
			if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0)
			{
				// Sort on item
				if (itemStack1.getItem() == itemStack2.getItem())
				{
					// Then sort on meta
					if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
					{
						// Then sort on NBT
						if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
						{
							// Then sort on stack size
							if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2))
							{
								return true;
							}
						}
						else
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static ItemStack cloneItemStack(ItemStack stack, int stackSize)
	{
		ItemStack clonedItemStack=stack.copy();
		clonedItemStack.stackSize=stackSize;
		return clonedItemStack;
	}
}

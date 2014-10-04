package com.sopa89.sopasbackpacks.utility;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.sopa89.sopasbackpacks.reference.Messages;
import com.sopa89.sopasbackpacks.reference.Names;

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

	public static void setOwner(ItemStack stack, EntityPlayer player)
	{
		NBTHelper.setString(stack, Names.NBT.OWNER, player.getDisplayName());
		NBTHelper.setLong(stack, Names.NBT.OWNER_UUID_MOST_SIG, player.getUniqueID().getMostSignificantBits());
		NBTHelper.setLong(stack, Names.NBT.OWNER_UUID_LEAST_SIG, player.getUniqueID().getLeastSignificantBits());
	}
	
	public static UUID getOwnerUUID(ItemStack stack)
	{
		if(NBTHelper.hasTag(stack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(stack, Names.NBT.OWNER_UUID_LEAST_SIG))
		{
			return new UUID(NBTHelper.getLong(stack, Names.NBT.OWNER_UUID_MOST_SIG), NBTHelper.getLong(stack, Names.NBT.OWNER_UUID_LEAST_SIG));
		}
		
		return null;
	}
	
	public static String getOwnerName(ItemStack stack)
	{
		if(NBTHelper.hasTag(stack, Names.NBT.OWNER))
		{
			return NBTHelper.getString(stack, Names.NBT.OWNER);
		}
		
		return StatCollector.translateToLocal(Messages.NO_OWNER);
	}

	public static boolean hasOwner(ItemStack stack)
	{
		return (NBTHelper.hasTag(stack, Names.NBT.OWNER_UUID_MOST_SIG) && NBTHelper.hasTag(stack, Names.NBT.OWNER_UUID_LEAST_SIG) || NBTHelper.hasTag(stack, Names.NBT.OWNER));
	}
}

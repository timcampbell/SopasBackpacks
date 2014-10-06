package com.sopa89.sopasbackpacks.item;

import com.sopa89.sopasbackpacks.SopasBackpacks;
import com.sopa89.sopasbackpacks.reference.GUIs;
import com.sopa89.sopasbackpacks.reference.Names;
import com.sopa89.sopasbackpacks.utility.ItemHelper;
import com.sopa89.sopasbackpacks.utility.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMessengerBag extends ItemsSB
{
	public ItemMessengerBag()
	{
		super();
		this.setUnlocalizedName("messenger");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			ItemHelper.setOwner(stack, player);
			
			NBTHelper.setUUID(stack);
			NBTHelper.setBoolean(stack, Names.NBT.BACKPACK_GUI_OPEN, true);
			player.openGui(SopasBackpacks.instance, GUIs.MESSENGER.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
		return stack;
	}
}

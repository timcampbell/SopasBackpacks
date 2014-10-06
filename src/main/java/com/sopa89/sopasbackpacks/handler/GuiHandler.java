package com.sopa89.sopasbackpacks.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.sopa89.sopasbackpacks.client.gui.inventory.GuiBackpack;
import com.sopa89.sopasbackpacks.client.gui.inventory.GuiMessengerBag;
import com.sopa89.sopasbackpacks.inventory.ContainerBackpack;
import com.sopa89.sopasbackpacks.inventory.ContainerMessengerBag;
import com.sopa89.sopasbackpacks.inventory.InventoryBackpack;
import com.sopa89.sopasbackpacks.inventory.InventoryMessengerBag;
import com.sopa89.sopasbackpacks.reference.GUIs;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(id==GUIs.BACKPACK.ordinal())
		{
			return new ContainerBackpack(player, new InventoryBackpack(player.getHeldItem()));
		}
		else if(id==GUIs.MESSENGER.ordinal())
		{
			return new ContainerMessengerBag(player, new InventoryMessengerBag(player.getHeldItem()));
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(id==GUIs.BACKPACK.ordinal())
		{
			return new GuiBackpack(player, new InventoryBackpack(player.getHeldItem()));
		}
		else if(id==GUIs.MESSENGER.ordinal())
		{
			return new GuiMessengerBag(player, new InventoryMessengerBag(player.getHeldItem()));
		}
		else
		{
			return null;
		}
	}

}

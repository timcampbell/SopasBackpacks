package com.sopa89.sopasbackpacks.init;

import com.sopa89.sopasbackpacks.item.ItemBackpack;
import com.sopa89.sopasbackpacks.item.ItemsSB;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems 
{
	public static ItemsSB backpack=new ItemBackpack();
	
	public static void init()
	{
		GameRegistry.registerItem(backpack, "Backpack");
	}
}

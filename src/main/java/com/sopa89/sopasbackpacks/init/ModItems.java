package com.sopa89.sopasbackpacks.init;

import com.sopa89.sopasbackpacks.item.ItemBackpack;
import com.sopa89.sopasbackpacks.item.ItemBackpackBag;
import com.sopa89.sopasbackpacks.item.ItemBackpackStrap;
import com.sopa89.sopasbackpacks.item.ItemStripLeather;
import com.sopa89.sopasbackpacks.item.ItemYarnSpool;
import com.sopa89.sopasbackpacks.item.ItemsSB;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems 
{
	public static ItemsSB backpack=new ItemBackpack();
	public static ItemsSB yarnSpool=new ItemYarnSpool();
	public static ItemsSB stripLeather=new ItemStripLeather();
	public static ItemsSB backpackBag=new ItemBackpackBag();
	public static ItemsSB backpackStrap=new ItemBackpackStrap();
	
	public static void init()
	{
		GameRegistry.registerItem(backpack, "backpack");
		GameRegistry.registerItem(yarnSpool, "yarnSpool");
		GameRegistry.registerItem(stripLeather, "stripLeather");
		GameRegistry.registerItem(backpackBag, "backpackBag");
		GameRegistry.registerItem(backpackStrap, "backpackStrap");
	}
}

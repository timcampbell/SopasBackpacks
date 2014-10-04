package com.sopa89.sopasbackpacks.reference;

import net.minecraft.init.Items;

public class Names 
{
	public static final class NBT
	{
		public static final String UUID_MOST_SIG="UUIDMostSig";
		public static final String UUID_LEAST_SIG="UUIDLeastSig";
		public static final String ITEMS="Items";
		public static final String OWNER="Owner";
		public static final String OWNER_UUID_MOST_SIG="OwnerUUIDMostSig";
		public static final String OWNER_UUID_LEAST_SIG="OwnerUUIDLeastSig";
		public static final String BACKPACK_GUI_OPEN="BackpackGuiOpen";
		
	}
	
	public static final class Container
	{
		public static final String BACKPACK="container.sopasbackpacks:"+Items.BACKPACK;
		public static final String VANILLA_INVENTORY="container.inventory";
	}
	
	public static final class Items
	{
		public static final String BACKPACK="backpack";
	}
}

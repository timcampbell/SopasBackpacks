package com.sopa89.sopasbackpacks.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.sopa89.sopasbackpacks.init.ModItems;
import com.sopa89.sopasbackpacks.reference.Reference;

public class CreativeTabSB
{
	public static final CreativeTabs SB_TAB=new CreativeTabs(Reference.MOD_ID)
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.backpack;
		}
		
		@Override
		public String getTranslatedTabLabel()
		{
			return "Sopa's Backpacks";
		}
	};
}

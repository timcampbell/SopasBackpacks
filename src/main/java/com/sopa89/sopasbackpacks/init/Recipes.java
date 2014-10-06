package com.sopa89.sopasbackpacks.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes 
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.stripLeather, 3), new ItemStack(Items.shears), new ItemStack(Items.leather)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.backpackStrap), " s ", " s ", " s ", 's', new ItemStack(ModItems.stripLeather)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.backpackBag), "lll", "lcl", "lll", 'l', new ItemStack(Items.leather), 'c', new ItemStack(Blocks.chest)));
		
		//Temp Backpack Recipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.backpack), "sbs", 's', new ItemStack(ModItems.backpackStrap), 'b', new ItemStack(ModItems.backpackBag)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.messengerBag), "sss", "b b", 's', new ItemStack(ModItems.backpackStrap), 'b', new ItemStack(ModItems.backpackBag)));
	}
}

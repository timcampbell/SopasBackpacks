package com.sopa89.sopasbackpacks;

import com.sopa89.sopasbackpacks.handler.ConfigurationHandler;
import com.sopa89.sopasbackpacks.handler.GuiHandler;
import com.sopa89.sopasbackpacks.init.ModBlocks;
import com.sopa89.sopasbackpacks.init.ModItems;
import com.sopa89.sopasbackpacks.init.Recipes;
import com.sopa89.sopasbackpacks.proxy.IProxy;
import com.sopa89.sopasbackpacks.reference.Reference;
import com.sopa89.sopasbackpacks.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory=Reference.GUI_FACTORY_CLASS)
public class SopasBackpacks 
{
	@Mod.Instance(Reference.MOD_ID)
	public static SopasBackpacks instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		ModBlocks.init();
		
		LogHelper.info("Pre-Initialization Complete");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		Recipes.init();
		
		LogHelper.info("Initialization Comlete");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post-Initialization Complete");
	}
}

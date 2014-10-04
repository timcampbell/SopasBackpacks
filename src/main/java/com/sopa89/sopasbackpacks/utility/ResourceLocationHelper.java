package com.sopa89.sopasbackpacks.utility;

import net.minecraft.util.ResourceLocation;

import com.sopa89.sopasbackpacks.reference.Reference;

public class ResourceLocationHelper 
{
	public static ResourceLocation getResourceLocation(String modID, String path)
	{
		return new ResourceLocation(modID, path);
	}
	
	public static ResourceLocation getResourceLocation(String path)
	{
		return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
	}
}

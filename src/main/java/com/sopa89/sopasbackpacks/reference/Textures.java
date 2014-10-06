package com.sopa89.sopasbackpacks.reference;

import net.minecraft.util.ResourceLocation;

import com.sopa89.sopasbackpacks.utility.ResourceLocationHelper;

public class Textures 
{
	public static final class Gui
	{
		private static final String GUI_SHEET_LOCATION="textures/gui/";
		public static final ResourceLocation BACKPACK=ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION+"backpackGui.png");
		public static final ResourceLocation MESSENGER_BAG=ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION+"messengerGui.png");
	}
}

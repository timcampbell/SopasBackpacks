package com.sopa89.sopasbackpacks.utility;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable 
{
	void readFromNBT(NBTTagCompound nbtTagCompound);
	
	void writeToNBT(NBTTagCompound nbtTagCompound);
}

package com.sopa89.sopasbackpacks.utility;

import java.util.UUID;

import com.sopa89.sopasbackpacks.reference.Names;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper 
{
	public static void clearStatefulNBTTags(ItemStack stack)
	{}
	
	public static boolean hasTag(ItemStack stack, String name)
	{
		return stack!=null && stack.stackTagCompound!=null && stack.stackTagCompound.hasKey(name);
	}
	
	public static void removeTag(ItemStack stack, String name)
	{
		if(stack.stackTagCompound!=null)
		{
			stack.stackTagCompound.removeTag(name);
		}
	}
	
	public static boolean hasUUID(ItemStack stack)
	{
		return hasTag(stack, Names.NBT.UUID_MOST_SIG) && hasTag(stack, Names.NBT.UUID_LEAST_SIG);
	}
	
	public static void setUUID(ItemStack stack)
	{
		initNBTTagCompound(stack);
		
		if(!hasTag(stack, Names.NBT.UUID_MOST_SIG) && !hasTag(stack, Names.NBT.UUID_LEAST_SIG))
		{
			UUID itemUUID=UUID.randomUUID();
			setLong(stack, Names.NBT.UUID_MOST_SIG, itemUUID.getMostSignificantBits());
			setLong(stack, Names.NBT.UUID_LEAST_SIG, itemUUID.getLeastSignificantBits());
		}
	}
	
	public static void initNBTTagCompound(ItemStack stack)
	{
		if (stack.stackTagCompound==null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static long getLong(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setLong(stack, name, (long)0);
		}
		
		return stack.stackTagCompound.getLong(name);
	}
	
	public static void setLong(ItemStack stack, String name, long value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setLong(name, value);
	}
	
	public static String getString(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setString(stack, name, "");
		}
		
		return stack.stackTagCompound.getString(name);
	}
	
	public static void setString(ItemStack stack, String name, String value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setString(name, value);
	}

	public static boolean getBoolean(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setBoolean(stack, name, false);
		}
		
		return stack.stackTagCompound.getBoolean(name);
	}
	
	public static void setBoolean(ItemStack stack, String name, boolean value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setBoolean(name, value);
	}
	
	public static byte getByte(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setByte(stack, name, (byte)0);
		}
		
		return stack.stackTagCompound.getByte(name);
	}
	
	public static void setByte(ItemStack stack, String name, byte value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setByte(name, value);
	}

	public static short getShort(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setShort(stack, name, (short)0);
		}
		
		return stack.stackTagCompound.getShort(name);
	}
	
	public static void setShort(ItemStack stack, String name, short value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setShort(name, value);
	}

	public static int getInt(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setInteger(stack, name, 0);
		}
		
		return stack.stackTagCompound.getInteger(name);
	}
	
	public static void setInteger(ItemStack stack, String name, int value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setInteger(name, value);
	}

	public static float getFloat(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setFloat(stack, name, (float)0);
		}
		
		return stack.stackTagCompound.getFloat(name);
	}
	
	public static void setFloat(ItemStack stack, String name, float value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setFloat(name, value);
	}

	public static double getDouble(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setDouble(stack, name, (double)0);
		}
		
		return stack.stackTagCompound.getDouble(name);
	}
	
	public static void setDouble(ItemStack stack, String name, double value)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setDouble(name, value);
	}

	public static NBTTagList getTagList(ItemStack stack, String name, int nbtBaseType)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setTagList(stack, name, new NBTTagList());
		}
		
		return stack.stackTagCompound.getTagList(name, nbtBaseType);
	}
	
	public static void setTagList(ItemStack stack, String name, NBTTagList tagList)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setTag(name, tagList);
	}

	public static NBTTagCompound getTagCompound(ItemStack stack, String name)
	{
		initNBTTagCompound(stack);
		
		if(!stack.stackTagCompound.hasKey(name))
		{
			setTagCompound(stack, name, new NBTTagCompound());
		}
		
		return stack.stackTagCompound.getCompoundTag(name);
	}
	
	public static void setTagCompound(ItemStack stack, String name, NBTTagCompound tagCompound)
	{
		initNBTTagCompound(stack);
		
		stack.stackTagCompound.setTag(name, tagCompound);
	}
}

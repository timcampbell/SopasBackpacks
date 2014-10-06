package com.sopa89.sopasbackpacks.inventory;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.sopa89.sopasbackpacks.reference.Names;
import com.sopa89.sopasbackpacks.utility.INBTTaggable;
import com.sopa89.sopasbackpacks.utility.NBTHelper;

public class InventoryMessengerBag implements IInventory, INBTTaggable
{
	public ItemStack parentStack;
	protected ItemStack[] inventory;
	protected String customName;
	
	public InventoryMessengerBag(ItemStack stack)
	{
		this.parentStack=stack;
		
		int size=ContainerMessengerBag.MESSENGER_BAG_ROWS*ContainerMessengerBag.MESSENGER_BAG_COLUMNS;
		inventory=new ItemStack[size];
		
		readFromNBT(stack.getTagCompound());
	}
	
	public void onGuiSaved(EntityPlayer player)
	{
		parentStack=findParentItemStack(player);
		
		if(parentStack!=null)
		{
			save();
		}
	}
	
	public ItemStack findParentItemStack(EntityPlayer player)
	{
		if(NBTHelper.hasUUID(parentStack))
		{
			UUID parentStackUUID=new UUID(parentStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG), parentStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG));
			
			for(int i=0; i<player.inventory.getSizeInventory(); i++)
			{
				ItemStack stack=player.inventory.getStackInSlot(i);
				
				if(NBTHelper.hasUUID(stack))
				{
					if(stack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG)==parentStackUUID.getMostSignificantBits())
					{
						return stack;
					}
				}
			}
		}
		
		return null;
	}

	public boolean matchesUUID(UUID uuid)
	{
		return NBTHelper.hasUUID(parentStack) && parentStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG)==uuid.getMostSignificantBits() && parentStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG)==uuid.getLeastSignificantBits();
	}

	public void save()
	{
		NBTTagCompound tagCompound=parentStack.getTagCompound();
		
		if(tagCompound==null)
		{
			tagCompound=new NBTTagCompound();
			
			UUID uuid=UUID.randomUUID();
			tagCompound.setLong(Names.NBT.UUID_MOST_SIG, uuid.getMostSignificantBits());
			tagCompound.setLong(Names.NBT.UUID_LEAST_SIG, uuid.getLeastSignificantBits());
		}
		
		writeToNBT(tagCompound);
		parentStack.setTagCompound(tagCompound);
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return inventory[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount)
	{
		ItemStack stack=getStackInSlot(slotIndex);
		
		if(stack!=null)
		{
			if(stack.stackSize<decrementAmount)
			{
				setInventorySlotContents(slotIndex, null);
			}
			else
			{
				stack=stack.splitStack(decrementAmount);
				if(stack.stackSize==0)
				{
					setInventorySlotContents(slotIndex, null);
				}
			}
		}
		
		
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		if(inventory[slotIndex]!=null)
		{
			ItemStack stack=inventory[slotIndex];
			inventory[slotIndex]=null;
			return stack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack stack)
	{
		inventory[slotIndex]=stack;
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomName() ? this.getCustomName() : Names.Container.MESSENGER_BAG;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
		//NOOP
	}
	
	@Override 
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory()
	{
		//NOOP
	}
	
	@Override
	public void closeInventory()
	{
		//NOOP
	}
	
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
	{
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		if(tagCompound!=null && tagCompound.hasKey(Names.NBT.ITEMS))
		{
			if(tagCompound.hasKey(Names.NBT.ITEMS))
			{
				NBTTagList tagList=tagCompound.getTagList(Names.NBT.ITEMS, 10);
				byte slotIndex=tagCompound.getByte("Slot");
				if(slotIndex>=0 && slotIndex<inventory.length)
				{
					inventory[slotIndex]=ItemStack.loadItemStackFromNBT(tagCompound);
				}
				
			}
			
			if(tagCompound.hasKey("display") && tagCompound.getTag("display").getClass().equals(NBTTagCompound.class))
			{
				if(tagCompound.getCompoundTag("diplay").hasKey("Name"))
				{
					customName=tagCompound.getCompoundTag("display").getString("Name");
				}
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		NBTTagList tagList=new NBTTagList();
		for(int currentIndex=0; currentIndex<inventory.length; currentIndex++)
		{
			if(inventory[currentIndex]!=null)
			{
				NBTTagCompound nbtTagCompound=new NBTTagCompound();
				nbtTagCompound.setByte("Slot", (byte)currentIndex);
				inventory[currentIndex].writeToNBT(nbtTagCompound);
				tagList.appendTag(nbtTagCompound);
			}
		}
		tagCompound.setTag(Names.NBT.ITEMS, tagList);
	}

	public boolean hasCustomName()
	{
		return customName!=null && customName.length()>0;
	}
	
	public String getCustomName()
	{
		return customName;
	}
}

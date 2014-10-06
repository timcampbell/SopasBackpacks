package com.sopa89.sopasbackpacks.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.sopa89.sopasbackpacks.inventory.ContainerMessengerBag;
import com.sopa89.sopasbackpacks.inventory.InventoryMessengerBag;
import com.sopa89.sopasbackpacks.reference.Colors;
import com.sopa89.sopasbackpacks.reference.Names;
import com.sopa89.sopasbackpacks.reference.Textures;
import com.sopa89.sopasbackpacks.utility.NBTHelper;

public class GuiMessengerBag extends GuiContainer
{
	private final ItemStack PARENT_STACK;
	private final InventoryMessengerBag MESSENGER_BAG_INVENTORY;
	
	public GuiMessengerBag(EntityPlayer player, InventoryMessengerBag inventory)
	{
		super(new ContainerMessengerBag(player, inventory));
		
		this.PARENT_STACK=player.getHeldItem();
		this.MESSENGER_BAG_INVENTORY=inventory;
		
		xSize=175;
		ySize=221;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		fontRendererObj.drawString(StatCollector.translateToLocal(MESSENGER_BAG_INVENTORY.getInventoryName()), 7, 6, Integer.parseInt(Colors.STANDARD_GRAY, 16));
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Container.VANILLA_INVENTORY), 7, ySize-97+5, Integer.parseInt(Colors.STANDARD_GRAY, 16));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.mc.getTextureManager().bindTexture(Textures.Gui.MESSENGER_BAG);
		
		int xStart=(width-xSize)/2;
		int yStart=(height-ySize)/2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		
		if(mc.thePlayer!=null)
		{
			for(ItemStack stack:mc.thePlayer.inventory.mainInventory)
			{
				if(stack!=null)
				{
					if(NBTHelper.hasTag(stack, Names.NBT.BACKPACK_GUI_OPEN))
					{
						NBTHelper.removeTag(stack, Names.NBT.BACKPACK_GUI_OPEN);
					}
				}
			}
		}
	}
}

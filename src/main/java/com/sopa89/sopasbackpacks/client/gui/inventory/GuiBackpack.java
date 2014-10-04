package com.sopa89.sopasbackpacks.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.sopa89.sopasbackpacks.inventory.ContainerBackpack;
import com.sopa89.sopasbackpacks.inventory.InventoryBackpack;
import com.sopa89.sopasbackpacks.reference.Colors;
import com.sopa89.sopasbackpacks.reference.Names;
import com.sopa89.sopasbackpacks.reference.Textures;
import com.sopa89.sopasbackpacks.utility.NBTHelper;

public class GuiBackpack extends GuiContainer
{
	private final ItemStack PARENT_STACK;
	private final InventoryBackpack BACKPACK_INVENTORY;
	
	public GuiBackpack(EntityPlayer player, InventoryBackpack backpackInv)
	{
		super(new ContainerBackpack(player, backpackInv));
		
		this.PARENT_STACK=backpackInv.parentStack;
		this.BACKPACK_INVENTORY=backpackInv;
		
		xSize=176;
		ySize=167;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		fontRendererObj.drawString(StatCollector.translateToLocal(BACKPACK_INVENTORY.getInventoryName()), 7, 6, Integer.parseInt(Colors.PURE_WHITE, 16));
		fontRendererObj.drawString(StatCollector.translateToLocal(Names.Container.VANILLA_INVENTORY), 7, ySize-97+5, Integer.parseInt(Colors.PURE_WHITE, 16));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.mc.getTextureManager().bindTexture(Textures.Gui.BACKPACK);
		
		int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		
		if(mc.thePlayer!=null)
		{
			for(ItemStack stack: mc.thePlayer.inventory.mainInventory)
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

package com.sopa89.sopasbackpacks.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.sopa89.sopasbackpacks.creativeTab.CreativeTabSB;
import com.sopa89.sopasbackpacks.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlocksSB extends Block
{
	public BlocksSB(Material material) 
	{
		super(material);
		this.setCreativeTab(CreativeTabSB.SB_TAB);
	}
	
	public BlocksSB()
	{
		super(Material.rock);
		this.setCreativeTab(CreativeTabSB.SB_TAB);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase()+":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

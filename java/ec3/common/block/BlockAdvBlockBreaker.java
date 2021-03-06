package ec3.common.block;

import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.common.mod.EssentialCraftCore;
import ec3.common.tile.TileAdvancedBlockBreaker;
import ec3.utils.cfg.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAdvBlockBreaker extends BlockContainer{
	
	public IIcon[] blockIcons = new IIcon[2];
	
	public BlockAdvBlockBreaker() {
		super(Material.rock);
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side)
    {
    	int meta = w.getBlockMetadata(x, y, z);
    	return side == meta ? blockIcons[1] : blockIcons[0];
    }
	
    public IIcon getIcon(int side, int meta)
    {
        return side == 3 ? blockIcons[1] : blockIcons[0];
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	this.blockIcons[0] = p_149651_1_.registerIcon("essentialcraft:fortifiedStone");
    	this.blockIcons[1] = p_149651_1_.registerIcon("essentialcraft:blockAdvBreaker");
    }
	
    public boolean canProvidePower()
    {
        return true;
    }
    
    public void onNeighborBlockChange(World w, int x, int y, int z, Block n) 
    {
    	if(w.isBlockIndirectlyGettingPowered(x, y, z))
    	{
    		TileAdvancedBlockBreaker.class.cast(w.getTileEntity(x, y, z)).breakBlocks();
    	}
    }
    
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return ForgeDirection.values()[side].ordinal();
    }
    
	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
		MiscUtils.dropItemsOnBlockBreak(par1World, par2, par3, par4, par5, par6);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileAdvancedBlockBreaker();
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
	    if (par1World.isRemote)
	    {
	        return true;
	    }else
	    {
	     	if(!par5EntityPlayer.isSneaking())
	       	{
	       		par5EntityPlayer.openGui(EssentialCraftCore.core, Config.guiID[0], par1World, par2, par3, par4);
	           	return true;
	       	}
	       	else
	       	{
	       		return false;
	       	}
	    }
	}
	
	
}

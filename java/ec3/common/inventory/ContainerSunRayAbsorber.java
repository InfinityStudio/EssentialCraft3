package ec3.common.inventory;

import ec3.common.tile.TileSunRayAbsorber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerSunRayAbsorber extends Container{
	
    private TileSunRayAbsorber rayTower;
    public ContainerSunRayAbsorber(InventoryPlayer par1InventoryPlayer, TileEntity par2)
    {
        this.rayTower = (TileSunRayAbsorber) par2;
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18,84+ i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return rayTower.isUseableByPlayer(p_75145_1_);
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        return null;
    }
}

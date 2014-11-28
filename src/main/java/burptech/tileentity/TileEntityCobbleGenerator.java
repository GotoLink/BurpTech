package burptech.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;

public class TileEntityCobbleGenerator extends TileEntity
{
    private int tickCount = 0;

    @Override
    public void updateEntity()
    {
        if (this.worldObj == null || this.worldObj.isRemote)
            return;

        ++tickCount;

        if (tickCount % 20 != 0)
            return;

        tickCount = 0;

        boolean hasWater = hasMaterialAsNeighbor(Material.water);
        boolean hasLava = hasMaterialAsNeighbor(Material.lava);
        boolean hasRedstone = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

        if (hasWater && hasLava && !hasRedstone)
        {
            ItemStack stack = new ItemStack(Blocks.cobblestone);

            IInventory inventory = getOutputInventory();

            if (inventory == null)
                return;

            TileEntityHopper.func_145889_a(inventory, stack, Facing.oppositeSide[getBlockMetadata()]);//insert

            if (stack == null || stack.stackSize == 0)
                playSoundFX();
        }
    }

    protected void playSoundFX()
    {
        worldObj.playSoundEffect((double) ((float) xCoord + 0.5F), (double) ((float) yCoord + 0.5F), (double) ((float) zCoord + 0.5F), "random.fizz", 0.25F, 2.6F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.8F);
    }

    private boolean hasMaterialAsNeighbor(Material material)
    {
        Material locatedMaterial;

        locatedMaterial = worldObj.getBlock(xCoord - 1, yCoord, zCoord).getMaterial();
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlock(xCoord + 1, yCoord, zCoord).getMaterial();
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlock(xCoord, yCoord - 1, zCoord).getMaterial();
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlock(xCoord, yCoord + 1, zCoord).getMaterial();
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlock(xCoord, yCoord, zCoord - 1).getMaterial();
        if (locatedMaterial == material)
            return true;

        locatedMaterial = worldObj.getBlock(xCoord, yCoord, zCoord + 1).getMaterial();
        return locatedMaterial == material;
    }

    private IInventory getOutputInventory()
    {
        int facing = getBlockMetadata();
        return TileEntityHopper.func_145893_b(this.getWorldObj(), (double) (this.xCoord + Facing.offsetsXForSide[facing]), (double) (this.yCoord + Facing.offsetsYForSide[facing]), (double) (this.zCoord + Facing.offsetsZForSide[facing]));
    }
}

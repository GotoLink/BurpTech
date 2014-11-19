package burptech.item;

import burptech.lib.Constants;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * Pulled from ProjectRed - https://github.com/MrTJP/ProjectRed/blob/master/common/mrtjp/projectred/exploration/ItemGemSickle.java
 */
public class ItemSickle extends ItemTool
{
    private int radiusCrops = 2;
    private int radiusLeaves = 1;
    private static int baseDamage = 3;

    public ItemSickle(ToolMaterial material)
    {
        super(baseDamage, material, Sets.newHashSet());
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public Item setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        setTextureName(Constants.MOD_ID + ":" + name);
        return this;
    }

    @Override
    public float getDigSpeed(ItemStack tool, Block block, int meta)
    {
        if (block instanceof BlockLeaves)
            return this.efficiencyOnProperMaterial;

        return super.getDigSpeed(tool, block, meta);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block b, int x, int y, int z, EntityLivingBase entity)
    {
        EntityPlayer player;

        if (entity instanceof EntityPlayer)
            player = (EntityPlayer) entity;
        else
            return false;

        if (b != null)
        {
            if (b.isLeaves(world, x, y, z))
                return recurseLeaves(stack, world, x, y, z, player);

            if (b instanceof BlockFlower)
                return recurseCrops(stack, world, x, y, z, player);
        }
        return super.onBlockDestroyed(stack, world, b, x, y, z, entity);
    }

    public boolean recurseLeaves(ItemStack stack, World w, int x, int y, int z, EntityPlayer player)
    {
        boolean used = false;
        for (int i = -radiusLeaves; i <= radiusLeaves; i++)
            for (int j = -radiusLeaves; j <= radiusLeaves; j++)
                for (int k = -radiusLeaves; k <= radiusLeaves; k++)
                {
                    int localX = x + i;
                    int localY = y + j;
                    int localZ = z + k;
                    Block localBlock = w.getBlock(localX, localY, localZ);
                    int meta = w.getBlockMetadata(localX, localY, localZ);
                    if (localBlock != null && (localBlock.isLeaves(w, localX, localY, localZ) || localBlock instanceof BlockLeaves))
                    {
                        if (localBlock.canHarvestBlock(player, meta))
                            localBlock.harvestBlock(w, player, localX, localY, localZ, meta);
                        w.setBlockToAir(localX, localY, localZ);
                        used = true;
                    }
                }
        if (used)
            stack.damageItem(1, player);

        return used;
    }

    public boolean recurseCrops(ItemStack stack, World w, int x, int y, int z, EntityPlayer player)
    {
        boolean used = false;
        for (int i = -radiusCrops; i <= radiusCrops; i++)
            for (int j = -radiusCrops; j <= radiusCrops; j++)
            {
                int localX = x + i;
                int localY = y;
                int localZ = z + j;
                Block localBlock = w.getBlock(localX, localY, localZ);
                int meta = w.getBlockMetadata(localX, localY, localZ);
                if (localBlock != null && (localBlock instanceof BlockFlower || localBlock instanceof IPlantable))
                {
                    if (localBlock.canHarvestBlock(player, meta))
                        localBlock.harvestBlock(w, player, localX, localY, localZ, meta);
                    w.setBlockToAir(localX, localY, localZ);
                    used = true;
                }
            }
        if (used)
            stack.damageItem(1, player);

        return used;
    }
}

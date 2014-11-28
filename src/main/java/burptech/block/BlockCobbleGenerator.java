package burptech.block;

import burptech.tileentity.TileEntityCobbleGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCobbleGenerator extends BlockContainer
{
    public BlockCobbleGenerator()
    {
        super(Material.rock);
        setLightOpacity(0);
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCobbleGenerator();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side != (metadata & 7) ? net.minecraft.init.Blocks.piston.getIcon(1, 0) : net.minecraft.init.Blocks.piston.getIcon(5, 5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {

    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int facing = determineOrientation(x, y, z, entityLivingBase);
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        //this.setDefaultDirection(world, x, y, z);
    }

    public static ForgeDirection getOrientation(int metadata)
    {
        return ForgeDirection.getOrientation(metadata & 7);
    }

    public static int determineOrientation(int x, int y, int z, EntityLivingBase entity)
    {
        if (MathHelper.abs((float)entity.posX - (float)x) < 2.0F && MathHelper.abs((float)entity.posZ - (float)z) < 2.0F)
        {
            double d0 = entity.posY + 1.82D - (double)entity.yOffset;

            if (d0 - (double)y > 2.0D)
            {
                return 1;
            }

            if ((double)y - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
}

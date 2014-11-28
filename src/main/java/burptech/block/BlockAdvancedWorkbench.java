package burptech.block;

import burptech.BurpTechCore;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAdvancedWorkbench extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    public BlockAdvancedWorkbench()
    {
        super(Material.wood);
        setLightOpacity(0);
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconBottom : (side != metadata ? this.blockIcon : this.iconFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "side");
        this.iconFront = par1IconRegister.registerIcon(this.getTextureName() + "front");
        this.iconTop = par1IconRegister.registerIcon(this.getTextureName() + "top");
        this.iconBottom = par1IconRegister.registerIcon(this.getTextureName() + "bottom");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityAdvancedWorkbench();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;

        if (world.isRemote)
            return true;

        player.openGui(BurpTechCore.instance, Constants.GUI_ADVANCED_WORKBENCH_ID, world, x, y, z);

        return true;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        BlockSimpleRock.setDefaultDirection(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = facing == 0 ? 2 : (facing == 1 ? 5 : (facing == 2 ? 3 : 4));
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);

        if (itemStack.hasDisplayName())
        {
            TileEntityAdvancedWorkbench tileEntity = (TileEntityAdvancedWorkbench)world.getTileEntity(x, y, z);
            tileEntity.setInventoryName(itemStack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block blockId, int metadata)
    {
        TileEntityAdvancedWorkbench tileEntity = (TileEntityAdvancedWorkbench)world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
            for (int i = 0; i < tileEntity.getSizeInventory(); ++i)
            {
                if (tileEntity.isCraftingGrid(i) || tileEntity.isCraftingResult(i))
                    continue;

                tileEntity.dropInWorld(world, tileEntity.getStackInSlot(i), x, y, z);
            }

            tileEntity.dropInWorld(world, tileEntity.itemOverflow, x, y, z);

            //world.func_96440_m(x, y, z, blockId);
        }
        super.breakBlock(world, x, y, z, blockId, metadata);
    }
}

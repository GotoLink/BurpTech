package burptech.block;

import burptech.client.render.EntityDropParticleFX;
import burptech.lib.Constants;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class BlockBurpTechFluid extends BlockFluidClassic {
    protected float particleRed;
    protected float particleGreen;
    protected float particleBlue;

    public BlockBurpTechFluid(Fluid fluid, Material material) {
        super(fluid, material);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon[] theIcon;
    protected boolean flammable;
    protected int flammability = 0;
    protected boolean canSetFires;

    @Override
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? this.theIcon[1] : this.theIcon[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        String icons = Constants.MOD_ID+ ":" + fluidName;
        this.theIcon = new IIcon[] { iconRegister.registerIcon(icons + "_still"), iconRegister.registerIcon(icons + "_flow") };
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block blockId) {
        super.onNeighborBlockChange(world, x, y, z, blockId);
        if (flammable && world.provider.dimensionId == -1) {
            world.newExplosion(null, x, y, z, 4F, true, true);
            world.setBlockToAir(x, y, z);
        }
    }

    public BlockBurpTechFluid setBurning(boolean burning)
    {
        canSetFires = burning;
        setTickRandomly(burning);
        return this;
    }

    public BlockBurpTechFluid setFlammable(boolean flammable) {
        this.flammable = flammable;
        return this;
    }

    public BlockBurpTechFluid setFlammability(int flammability) {
        this.flammability = flammability;
        return this;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return flammable ? 300 : 0;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return flammability;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return flammable;
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return flammable && flammability == 0;
    }

    public BlockBurpTechFluid setParticleColor(float particleRed, float particleGreen, float particleBlue) {
        this.particleRed = particleRed;
        this.particleGreen = particleGreen;
        this.particleBlue = particleBlue;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);

        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement()) {
            double px = (double) ((float) x + rand.nextFloat());
            double py = (double) y - 1.05D;
            double pz = (double) ((float) z + rand.nextFloat());

            EntityFX fx = new EntityDropParticleFX(world, px, py, pz, particleRed, particleGreen, particleBlue);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
        }
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
            return false;

        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
            return false;

        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        // if you don't call this, the liquid does not flow....
        super.updateTick(world, x, y, z, random);

        // if this block can set fires, lets do the same logic that the lava does here
        if (!canSetFires)
            return;

        int nextRandom = random.nextInt(3);
        int i1;
        Block j1;

        for (i1 = 0; i1 < nextRandom; ++i1)
        {
            x += random.nextInt(3) - 1;
            ++y;
            z += random.nextInt(3) - 1;
            j1 = world.getBlock(x, y, z);

            if (j1 == net.minecraft.init.Blocks.air)
            {
                if (this.isFlammable(world, x - 1, y, z) || this.isFlammable(world, x + 1, y, z) || this.isFlammable(world, x, y, z - 1) || this.isFlammable(world, x, y, z + 1) || this.isFlammable(world, x, y - 1, z) || this.isFlammable(world, x, y + 1, z))
                {
                    world.setBlock(x, y, z, net.minecraft.init.Blocks.fire);
                    return;
                }
            }
            else if (j1.getMaterial().blocksMovement())
            {
                return;
            }
        }

        if (nextRandom == 0)
        {
            i1 = x;
            int j2 = z;

            for (int k1 = 0; k1 < 3; ++k1)
            {
                x = i1 + random.nextInt(3) - 1;
                z = j2 + random.nextInt(3) - 1;

                if (world.isAirBlock(x, y + 1, z) && this.isFlammable(world, x, y, z))
                {
                    world.setBlock(x, y + 1, z, net.minecraft.init.Blocks.fire);
                }
            }
        }
    }

    private boolean isFlammable(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlock(par2, par3, par4).getMaterial().getCanBurn();
    }
}

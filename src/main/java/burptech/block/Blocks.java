package burptech.block;

import burptech.BurpTechConfig;
import burptech.entity.living.tweaks.EntityPlayerEventHandler;
import burptech.lib.Constants;
import burptech.tileentity.TileEntityAdvancedWorkbench;
import burptech.tileentity.TileEntityCobbleGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/*
 * Block definitions for BurpTech
 */
public final class Blocks
{
	public Block blockIlluminatedCocoaOn;
    public Block blockNetherCoal;
    public Block blockNetherFluid;
    public Fluid fluidNetherFluid;
    public Block blockAdvancedWorkbench;
    public Block blockCobbleGenerator;

	public Blocks(BurpTechConfig configuration)
	{
        // create blocks
        if (configuration.enableIlluminatedCocoa.getBoolean(true))
		    addIlluminatedCocoa();
        if (configuration.enableNetherTechSolidFuels.getBoolean(true))
            addNetherCoalBlocks();
        if (configuration.enableNetherTechLiquidFuels.getBoolean(true))
            addNetherFuelBlocks();

        if (configuration.recipeAdvancedWorkbench.getBoolean(true))
        {
            blockAdvancedWorkbench = new BlockAdvancedWorkbench().setHardness(5.0f).setResistance(10.0f).setStepSound(Block.soundTypeWood).setBlockName("blockAdvancedWorkbench").setBlockTextureName(Constants.MOD_ID()+"advanced_workbench_");
            blockAdvancedWorkbench.setHarvestLevel("axe", 1);
            GameRegistry.registerBlock(blockAdvancedWorkbench, "blockAdvancedWorkbench");
            GameRegistry.registerTileEntity(TileEntityAdvancedWorkbench.class, "AdvancedWorkbench");
        }

        if (configuration.recipeCobbleGenerator.getBoolean(true))
        {
            blockCobbleGenerator = new BlockCobbleGenerator().setHardness(5.0f).setResistance(10.0f).setStepSound(Block.soundTypeStone).setBlockName("blockCobbleGenerator");
            blockCobbleGenerator.setHarvestLevel("pickaxe", 1);
            GameRegistry.registerBlock(blockCobbleGenerator, "blockCobbleGenerator");
            GameRegistry.registerTileEntity(TileEntityCobbleGenerator.class, "CobblestoneGenerator");
        }
	}

    private void addNetherCoalBlocks()
    {
        blockNetherCoal = new BlockSimpleRock().setBlockName("blockNetherCoal").setCreativeTab(CreativeTabs.tabMaterials).setBlockTextureName(Constants.MOD_ID()+ "nether_coal_block");
        GameRegistry.registerBlock(blockNetherCoal, "blockNetherCoal");
    }

    private void addNetherFuelBlocks()
    {
        fluidNetherFluid = new Fluid("nether").setDensity(3000).setViscosity(6000).setTemperature(1300).setLuminosity(15);
        FluidRegistry.registerFluid(fluidNetherFluid);

        blockNetherFluid = new BlockBurpTechFluid(fluidNetherFluid, Material.lava).setBurning(true).setParticleColor(131,24,24).setBlockName("blockNetherFluid");
        GameRegistry.registerBlock(blockNetherFluid, "blockNetherFluid");
    }

	private void addIlluminatedCocoa()
	{
		blockIlluminatedCocoaOn = new BlockCocoa().setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setLightLevel(0.9375F).setBlockName("cocoa").setBlockTextureName("cocoa");
        GameRegistry.registerBlock(blockIlluminatedCocoaOn, "blockLightingCocoa");
        MinecraftForge.EVENT_BUS.register(new EntityPlayerEventHandler());
    }
}
